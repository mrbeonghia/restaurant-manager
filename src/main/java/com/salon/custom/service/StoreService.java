package com.salon.custom.service;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.salon.base.core.BaseService;
import com.salon.base.security.CurrentUserDetailsContainer;
import com.salon.custom.dto.FileDTO;
import com.salon.custom.dto.store.*;
import com.salon.custom.dto.user.UserDTO;
import com.salon.custom.entities.StaffEntity;
import com.salon.custom.entities.StoreEntity;
import com.salon.custom.entities.UserAdmin;
import com.salon.custom.enums.Roles;
import com.salon.custom.exception.BaseException;
import com.salon.custom.repository.StoreRepository;
import com.salon.custom.security.role.annotation.IsStaffUser;
import com.salon.custom.security.role.annotation.IsSystemUser;
import com.salon.custom.util.Constant;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.*;



@Service
public class StoreService extends BaseService<StoreEntity, StoreRepository> {

    @Autowired
    private UserAdminService userAdminService;

    @Autowired
    private CurrentUserDetailsContainer userDetailsContainer;


    @Autowired
    private FileService fileService;

    @Value("${domain.app}")
    private String domain;


    public List<StoreEntity> findByIdIn(Set<Long> storeIds) {
        return repository.findByIdInAndIsDeletedFalse(storeIds);
    }

    public StoreDTO findByStoreId(Long storeId) {
        return repository.findByStoreId(storeId);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreService.class);

    @IsSystemUser
    @Transactional
    public StoreResponse createStore(StoreRequest storeRequest) {
        UserAdmin userAdmin = getCurrentUser().getUserAdmin();
        boolean isSystemAdmin = userAdmin.getType().equals(Roles.SYSTEM_ADMIN.getName());
        StoreResponse storeResponse = new StoreResponse();
        StoreEntity storeEntity = new StoreEntity();

        storeRequestToEntity(storeRequest, storeEntity, isSystemAdmin);

        storeEntity.setBooking(true);
        int numberStore = repository.countStore();
        storeEntity.setPosition(numberStore + 1);

        String randomUUID = UUID.randomUUID().toString();
        String qrCode = domain + "staff/" + randomUUID;
        storeEntity.setQrCodeUUID(qrCode);
//        storeEntity.setQrCodeImage(createQrCode(qrCode));
        repository.save(storeEntity);
        // create use own store
        try {
            UserAdmin userOwner = userAdminService.registerUserAdminOwnStore(storeRequest);
            UserDTO userDTO = new UserDTO();
            if (userOwner != null) {
                storeEntity.setOwnerId(userOwner.getId());
                userDTO.setId(userOwner.getId());
                userDTO.setName(userOwner.getName());
                userDTO.setUserName(userOwner.getUserName());
                userDTO.setEmail(userOwner.getEmail());
                userDTO.setPasswordOwner(userAdminService.decodePasswordOwnerStore(userOwner.getPasswordEncode()));
                userOwner.setStoreId(storeEntity.getId());
                userAdminService.save(userOwner);
            }
            preSave(storeEntity);
            StoreEntity storeEntityRequest = repository.save(storeEntity);
            StoreDTO storeDTO = toDto(storeEntityRequest);
            storeDTO.setUserOwner(userDTO);
            storeResponse.setStoreDTO(storeDTO);
            storeResponse.setCode(200);
        } catch (BaseException e) {
            storeEntity.setDeleted(true);
            update(storeEntity);
            storeResponse.setSuccess(false);
            storeResponse.setMessage(e.getMessage());
            storeResponse.setCode(e.getCode());
        }

        return storeResponse;
    }

    private void storeRequestToEntity(StoreRequest storeRequest, StoreEntity storeEntity, boolean isSystemUser) {
        storeEntity.setAddress(storeRequest.getAddress());
        storeEntity.setDescription(storeRequest.getDescription());
        storeEntity.setCloseTime(storeRequest.getCloseTime());
        storeEntity.setOpenTime(storeRequest.getOpenTime());
        storeEntity.setLatitude(storeRequest.getLatitude());
        storeEntity.setLongitude(storeRequest.getLongitude());
        storeEntity.setName(storeRequest.getName());
        storeEntity.setPhone(storeRequest.getPhoneNumber());
        storeEntity.setImageMapUrl(storeRequest.getImageMapUrl());
        storeEntity.setStoreUrl(storeRequest.getStoreUrl());
        storeEntity.setBed(storeRequest.getBed());
        storeEntity.setStoreCode(storeRequest.getStoreCode());
        if (storeRequest.getImages() != null) {
            storeEntity.setImageUrl(arrayImageToJson(storeRequest.getImages()));
        }
        if (storeRequest.getPointCheckIn() == null) {
            storeEntity.setPointCheckIn(0L);
        } else {
            storeEntity.setPointCheckIn(storeRequest.getPointCheckIn());
        }
        if (storeRequest.getPointReview() == null) {
            storeEntity.setPointReview(0L);
        } else {
            storeEntity.setPointReview(storeRequest.getPointReview());
        }
        if (isSystemUser) {
            storeEntity.setStoreCode(storeRequest.getStoreCode());
        }
    }

    public Long findByOwnerId(Long ownerId) {
        return repository.findByOwnerId(ownerId);
    }

    public StoreEntity findStoreByOwnerId(Long ownerId) {
        return repository.findByOwnerIdAndIsDeletedFalse(ownerId);
    }

    @IsSystemUser
    @Transactional
    public StoreResponse deleteStore(Long storeId) {
        UserAdmin userAdmin = getCurrentUser().getUserAdmin();
        if (!userAdmin.getType().equals(Roles.SYSTEM_ADMIN.getName())) {
            return new StoreResponse("This account is not authorized", 4001);
        }
        StoreEntity storeEntity = repository.findByIdAndIsDeletedFalse(storeId);
        if (storeEntity == null) {
            return new StoreResponse("This store not found", 4004);
        }
        storeEntity.setDeleted(true);
        update(storeEntity);
        // delete account manager
        userAdminService.deleteAccountManagerStore(storeEntity.getOwnerId());

        return new StoreResponse();
    }

    @IsSystemUser
    @Transactional
    public StoreResponse updateStore(StoreRequest storeRequest) {
        UserAdmin userAdmin = getCurrentUser().getUserAdmin();
        boolean isSystemAdmin = userAdmin.getType().equals(Roles.SYSTEM_ADMIN.getName());
        StoreEntity storeEntity = repository.findByIdAndIsDeletedFalse(storeRequest.getId());
        if (storeEntity == null) {
            return new StoreResponse("This store not found", 4004);
        }
        storeRequestToEntity(storeRequest, storeEntity, isSystemAdmin);

        UserAdmin userOwner = userAdminService.findByIdAndIsDeletedFalse(storeEntity.getOwnerId());
        if (userOwner == null) {
            return new StoreResponse("This store not have admin", 4005);
        }
        StoreResponse checkEmailOwner = setAdminOwnerOfStore(storeRequest, userOwner, isSystemAdmin);
        if (checkEmailOwner != null) return checkEmailOwner;

        StoreEntity storeResult = update(storeEntity);
        StoreDTO storeDTO = toDto(storeResult);
        userAdminToDTO(userOwner, storeDTO);

        return new StoreResponse(storeDTO);
    }

    private StoreResponse setAdminOwnerOfStore(StoreRequest storeRequest, UserAdmin userAdmin, boolean isSystemAdmin) {
        boolean check = false;
        if (storeRequest.getEmail() != null && isSystemAdmin) {
            if (!storeRequest.getEmail().equals(userAdmin.getEmail())
                    && userAdminService.checkEmailExist(storeRequest.getEmail())) {
                return new StoreResponse("Email exist", 4003);
            }

            if (!storeRequest.getEmail().equals(userAdmin.getEmail())) {
                userAdmin.setEmail(storeRequest.getEmail());
                userAdmin.setUserName(storeRequest.getEmail());
                check = true;
            }
        }
        if (!userAdminService.matchPassword(storeRequest.getPassword(), userAdmin.getPassword())) {
            check = true;
            userAdmin.setPassword(userAdminService.passwordEncoder(storeRequest.getPassword()));
            userAdmin.setPasswordEncode(userAdminService.encodePasswordCleaner(storeRequest.getPassword()));
        }
        if (storeRequest.getName() != null) {
            check = true;
            userAdmin.setName(storeRequest.getNameOwner());
        }
        if (check) {
            userAdminService.save(userAdmin);
        }
        return null;
    }

    public StoreDTO toDto(StoreEntity storeEntity) {
        StoreDTO dto = new StoreDTO();
        dto.setBooking(storeEntity.getBooking());
        dto.setId(storeEntity.getId());
        dto.setAddress(storeEntity.getAddress());
        dto.setCloseTime(storeEntity.getCloseTime());
        dto.setOpenTime(storeEntity.getOpenTime());
        dto.setDescription(storeEntity.getDescription());
        dto.setImageMapUrl(storeEntity.getImageMapUrl());
        dto.setLatitude(storeEntity.getLatitude());
        dto.setLongitude(storeEntity.getLongitude());
        dto.setName(storeEntity.getName());
        dto.setPhoneNumber(storeEntity.getPhone());
        dto.setPosition(storeEntity.getPosition());
        String jsonImage = storeEntity.getImageUrl();
        if (jsonImage != null) {
            dto.setImages(populateImageStore(jsonImage));
        }
        dto.setQrCodeImage(storeEntity.getQrCodeImage());
        dto.setQrCodeUUID(storeEntity.getQrCodeUUID());
        dto.setStoreUrl(storeEntity.getStoreUrl());
        dto.setPointCheckIn(storeEntity.getPointCheckIn());
        dto.setPointReview(storeEntity.getPointReview());
        dto.setBed(storeEntity.getBed());
        dto.setStoreCode(storeEntity.getStoreCode());

        return dto;
    }

    List<ImageStore> populateImageStore(String jsonImage) {
        List<ImageStore> itemImages = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(jsonImage);
        for (int index = 0; index < jsonArray.length(); index++) {
            JSONObject jsonObject = jsonArray.getJSONObject(index);
            ImageStore imageStore = new ImageStore();
            imageStore.setDefault(jsonObject.getBoolean("isDefault"));
            imageStore.setId(jsonObject.getString("id"));
            imageStore.setImageUrl(jsonObject.getString("imageUrl"));
            itemImages.add(imageStore);
        }
        return itemImages;
    }


    private String arrayImageToJson(List<String> imageList) {
        JSONArray array = new JSONArray();
        for (int index = 0; index < imageList.size(); index++) {
            JSONObject imageObject = new JSONObject();
            imageObject.put("id", UUID.randomUUID().toString());
            imageObject.put("imageUrl", imageList.get(index));
            imageObject.put("isDefault", index == 0);
            array.put(imageObject);
        }
        return array.toString();
    }

    public StoreResponse getAllStoreForApp(Pageable pageable) {
        StoreResponse storeResponse = new StoreResponse();

        Page<StoreEntity> storeEntities = repository.getAllStoreForApp(pageable);
        if (!storeEntities.isEmpty()) {
            List<StoreDTO> storeDTOList = new ArrayList<>();
            for (StoreEntity storeEntity : storeEntities) {
                storeDTOList.add(toDto(storeEntity));
            }
            storeResponse.setStoreList(storeDTOList);
            storeResponse.setPageDto(populatePageDto(storeEntities));
        }

        return storeResponse;
    }

    public StoreResponse actionBookingStore(UserAdmin userAdmin, Long storeId, String action) {
        StoreResponse storeResponse = new StoreResponse();
        if (userAdmin != null) {
            StoreEntity storeEntity = repository.findByIdAndIsDeletedFalse(storeId);
            if (storeEntity != null) {
                if ("ON".equals(action)) {
                    storeEntity.setBooking(true);
                    save(storeEntity);
                }
                if ("OFF".equals(action)) {
                    storeEntity.setBooking(false);
                    save(storeEntity);
                }
            } else {
                storeResponse.setSuccess(false);
            }
        } else {
            storeResponse.setSuccess(false);
        }
        return storeResponse;
    }

    public StoreResponse getAllStoreBookForApp() {
        StoreResponse storeResponse = new StoreResponse();
        UserAdmin userAdmin = getCurrentUser().getUserAdmin();
        List<StoreEntity> storeEntities;
        if (userAdmin != null) {
            storeEntities = repository.getAllStoreBooking();
        } else {
            storeEntities = repository.getAllStoreBookForApp();
        }

        List<StoreDTO> storeDTOList = new ArrayList<>();
        for (StoreEntity storeEntity : storeEntities) {
            StoreDTO storeDTO = toDto(storeEntity);

            storeDTOList.add(storeDTO);
        }
        storeResponse.setStoreList(storeDTOList);
        return storeResponse;
    }

    @Transactional
    public StoreResponse changePosition(ChangePositionRequest changePositionRequest) {
        StoreResponse storeResponse = new StoreResponse();
        if (changePositionRequest != null && changePositionRequest.getStorePosition() != null) {
            List<List<Long>> arrayPosition = changePositionRequest.getStorePosition();
            /*int update = repository.updatePosition(arrayPosition);
            if (update < 0) {
                storeResponse.setSuccess(false);
            }*/
        } else {
            storeResponse.setSuccess(false);
        }

        return storeResponse;
    }

    public StoreResponse getAllStoreForAdmin(Pageable pageable) {
        StoreResponse storeResponse = new StoreResponse();

        Page<StoreEntity> storeEntities = repository.getAllStoreForApp(pageable);
        if (!storeEntities.isEmpty()) {
            List<Long> userAdminIds = new ArrayList<>();
            for (StoreEntity storeEntity : storeEntities) {
                userAdminIds.add(storeEntity.getOwnerId());
            }
            Map<Long, UserDTO> userDTOMap = new HashMap<>();
            if (userAdminIds.size() > 0) {
                List<UserAdmin> userAdmins = userAdminService.findByIdInAndIsDeletedFalse(userAdminIds);
                for (UserAdmin userAdmin : userAdmins) {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setName(userAdmin.getName());
                    userDTO.setUserName(userAdmin.getUserName());
                    userDTO.setPasswordOwner(userAdminService.decodePasswordOwnerStore(userAdmin.getPasswordEncode()));
                    userDTO.setId(userAdmin.getId());
                    userDTO.setEmail(userAdmin.getEmail());
                    userDTOMap.put(userAdmin.getId(), userDTO);
                }
            }
            List<StoreDTO> storeDTOList = new ArrayList<>();
            for (StoreEntity storeEntity : storeEntities) {
                StoreDTO storeDTO = toDto(storeEntity);
                storeDTO.setUserOwner(userDTOMap.get(storeEntity.getOwnerId()));
                storeDTOList.add(storeDTO);
            }
            storeResponse.setStoreList(storeDTOList);
            storeResponse.setPageDto(populatePageDto(storeEntities));
        }

        return storeResponse;
    }


    public StoreResponse getAllStoreFilterForAdmin(Pageable pageable) {
        StoreResponse storeResponse = new StoreResponse();

        Page<StoreEntity> storeEntities = repository.getAllStoreFilterForAdmin(pageable);
        if (!storeEntities.isEmpty()) {

            List<StoreDTO> storeDTOList = new ArrayList<>();
            for (StoreEntity storeEntity : storeEntities) {
                storeDTOList.add(toDto(storeEntity));
            }
            storeResponse.setPageDto(populatePageDto(storeEntities));
            storeResponse.setStoreList(storeDTOList);
        }

        return storeResponse;
    }

    public StoreResponse findStoreId(Long storeId) {
        StoreResponse storeResponse = new StoreResponse();
        StoreEntity storeEntity = repository.findByIdAndIsDeletedFalse(storeId);
        if (storeEntity != null) {
            storeResponse.setStoreDTO(toDto(storeEntity));
        } else {
            storeResponse.setMessage("Can't not find store");
        }
        return storeResponse;
    }

    @IsSystemUser
    public StoreResponse findStoreByOwner() {
        UserAdmin userAdmin = userDetailsContainer.getUserDetails().getUserAdmin();
        StoreResponse storeResponse = new StoreResponse();
        if (userAdmin == null || !Roles.STORE_ADMIN.getName().equals(userAdmin.getType())) {
            return new StoreResponse("This account not authorize", 4001);
        }
        StoreEntity storeEntity = repository.findByIdAndIsDeletedFalse(userAdmin.getStoreId());
        if (storeEntity == null) {
            return new StoreResponse("This store not found", 4004);
        }
        StoreDTO storeDTO = toDto(storeEntity);
        userAdminToDTO(userAdmin, storeDTO);
        storeResponse.setStoreDTO(storeDTO);

        return storeResponse;
    }


    private void userAdminToDTO(UserAdmin userAdmin, StoreDTO storeDTO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(userAdmin.getName());
        userDTO.setUserName(userAdmin.getUserName());
        userDTO.setId(userAdmin.getId());
        userDTO.setEmail(userAdmin.getEmail());
        userDTO.setPasswordOwner(userAdminService.decodePasswordOwnerStore(userAdmin.getPasswordEncode()));
        storeDTO.setUserOwner(userDTO);
    }

    public StoreEntity getStoreById(Long storeId) {
        return repository.findByIdAndIsDeletedFalse(storeId);
    }

    public Page<StoreEntity> getAllStoreId(Pageable pageable) {
        return repository.findByIsDeleted(pageable, false);
    }

    public String getStoreNameById(Long storeId) {
        return repository.findStoreNameById(storeId);
    }


    private String decodeQrCode(URL url) throws FormatException, ChecksumException, NotFoundException, IOException {
        BufferedImage image = ImageIO.read(url);
        if (image == null) {
            throw new IllegalStateException("Can not load qrCode!");
        }
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader qrCodeReader = new QRCodeReader();
        Result result = qrCodeReader.decode(bitmap);
        return result.getText();
    }

    /*public String createQrCode(String randomUUID) {
        FileDTO fileDTO = fileService.uploadFileBytes(getQRCodeImage(randomUUID, 400, 400),
                "image", UUID.randomUUID() + ".png", "qr_code");
        return fileDTO.getFileUrl();
    }*/

    public StoreEntity getStoreByQrcode(String qrCode) {
        return repository.findByQrCodeUUIDAndIsDeletedFalse(qrCode);
    }

    public StoreResponse updateQrCodeStore() {
        StoreResponse storeResponse = new StoreResponse();
        List<StoreEntity> storeEntities = repository.findAllByIsDeletedFalse();
        if (!storeEntities.isEmpty()) {
            for (StoreEntity storeEntity : storeEntities) {
                String randomUUID = UUID.randomUUID().toString();
                String qrCode = domain + "staff/" + randomUUID;
                storeEntity.setQrCodeUUID(qrCode);
//                storeEntity.setQrCodeImage(createQrCode(qrCode));
                repository.save(storeEntity);
            }
            storeResponse.setCode(200);
        } else {
            storeResponse.setCode(4003);
        }
        return storeResponse;
    }

    public List<StoreEntity> getListStore() {
        return repository.findAllByIsDeletedFalse();
    }


    public List<StoreEntity> getListStoreByIds(List<Long> storeIds) {
        return repository.findByIdInAndIsDeletedFalse(storeIds);
    }

    public List<StoreEntity> getListStoreByIds(Set<Long> storeIds) {
        return repository.findByIdInAndIsDeletedFalse(storeIds);
    }

    public StoreEntity findStoreById(Long storeId) {
        return repository.findByIdAndIsDeletedFalse(storeId);
    }

    public Integer getMaxBedOfStore(Long storeId) {
        return repository.findMaxBedOfStore(storeId);
    }

    public StoreResponse updatePointDefaultOfStore() {
        StoreResponse storeResponse = new StoreResponse();
        List<StoreEntity> storeEntities = repository.findAllByIsDeletedFalse();
        if (!storeEntities.isEmpty()) {
            for (StoreEntity storeEntity : storeEntities) {
                storeEntity.setPointCheckIn(Constant.POINT_STORE_DEFAULT);
                storeEntity.setPointReview(Constant.POINT_STORE_DEFAULT);
                repository.save(storeEntity);
            }
            storeResponse.setCode(200);
        } else {
            storeResponse.setCode(4003);
        }
        return storeResponse;
    }

    public List<StoreEntity> findStoreByIdIn(List<Long> storeIds) {
        return repository.findByIdInAndIsDeletedFalse(storeIds);
    }


}
