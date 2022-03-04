package com.salon.custom.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.salon.base.core.BaseService;
import com.salon.base.security.CurrentUserDetailsContainer;
import com.salon.custom.dto.FileDTO;
import com.salon.custom.dto.booking.UserBookingRequest;
import com.salon.custom.dto.booking.user_booking.BookingInfoRequest;
import com.salon.custom.dto.user.*;
import com.salon.custom.dto.user.user_update_info.UserUpdateInfoDTO;
import com.salon.custom.dto.user.user_update_info.UserUpdateInfoResponse;
import com.salon.custom.dto.user.user_visit.UserVisitDTO;
import com.salon.custom.dto.user.user_visit.UserVisitResponse;
import com.salon.custom.entities.*;
import com.salon.custom.enums.CustomerType;
import com.salon.custom.enums.Roles;
import com.salon.custom.enums.UserType;
import com.salon.custom.exception.*;
import com.salon.custom.regex.Regex;
import com.salon.custom.security.CustomUserDetail;
import com.salon.custom.repository.UserRepository;
import com.salon.custom.security.role.annotation.IsAppUser;
import com.salon.custom.service.authentication.AuthenticationService;
import com.salon.custom.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailSendException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService extends BaseService<UserEntity, UserRepository> {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    @Autowired
    private FileService fileService;


    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CurrentUserDetailsContainer userDetailsContainer;

    public CustomUserDetail getCurrentUser() {
        return this.userDetailsContainer.getUserDetails();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Value("${domain.app}")
    private String domain;


//    public UserEntity findUser(String username) {
//        return repository.findUserByUserName(username);
//    }

//    public UserEntity findUserByEmail()(String email) {
//        return repository.findUserByEmail()(email);
//    }

    public UserEntity findByIdAndIsDeletedFalse(Long id) {
        return repository.findByIdAndIsDeletedFalse(id);
    }

    UserDTO findByUserId(Long id) {
        return repository.findByUserId(id);
    }

    void deleteUserOldDeleted(String email) {
        repository.deleteUserOldDeleted(email);
    }

    Page<UserEntity> findAllAndIsDeletedFalse(String search, Long storeId, Pageable pageable, String sex, String sort) {
        if (sort == null) {
            return repository.findAllAndIsDeletedFalse(search, storeId, sex, pageable);
        } else {
            return repository.findAllSortByCreatedTime(search, storeId, sex, pageable);
        }
    }

    List<UserEntity> findAllListUser(String search, Long storeId, String sex, String sort) {
//        if (rank != null) {
//            return repository.findAllAndIsDeletedFalse(search, rank.toLowerCase(), storeId, sex, pageable);
//        } else {
        if (sort == null) {
            return repository.findAllListUserAsc(search, storeId, sex);
        } else {
            return repository.findAllListUserDesc(search, storeId, sex);
        }
//        }
    }

    Page<UserEntity> findUserAppForAdmin(String search, Pageable pageable) {
        return repository.findUserAppByNamOrPhoneOrEmailForAdmin(search, pageable);
    }


    Set<Long> findByStoreIdAndIsDeletedFalse(Long storeId) {
        return repository.findByStoreIdAndIsDeletedFalse(storeId);
    }

    List<UserEntity> findByPhoneNumber(String phone) {
        return repository.findByPhoneNumberAndIsDeletedFalse(phone);
    }

    public UserResponse registerSendOtpEmail(String email) throws BaseException {
        try {
            UserEntity userEntity = repository.findUserByEmail(email);
            if (userEntity != null) {
                throw new BaseException("Email exist register", 4006);
            }
            UserResponse userResponse = new UserResponse();
            userResponse.setSuccess(true);
            userResponse.setMessage("Sent Otp success");
            userResponse.setCode(200);
            return userResponse;
        } catch (MailSendException e) {
            LOGGER.error(e.getMessage());
            throw new BaseException("Send otp fail", 4001);
//            return new UserResponse("Send otp fail", 4001);
        }
    }



    List<UserEntity> findByIdIn(Set<Long> userIds) {
        return repository.findByIdIn(userIds);
    }


    @IsAppUser
    public UserResponse getInfoUser() {
        UserResponse userResponse = new UserResponse();
        UserEntity userEntity = getCurrentUser().getUserEntity();

        if (userEntity != null) {
            Optional<UserEntity> user = repository.findById(userEntity.getId());
            if (user.isPresent()) {
                UserDTO userDTO = new UserDTO();
                populateUserDTO(userDTO, user.get());
                userResponse.setUser(userDTO);
            }
        } else {
            userResponse.setSuccess(false);
            userResponse.setMessage("Can not get info user");
        }
        return userResponse;
    }

    public UserResponse changePassword(ChangePasswordRequest changePasswordRequest) {
        UserResponse userResponse = new UserResponse();
        UserEntity userEntity = getCurrentUser().getUserEntity();
        if (userEntity != null) {
            UserEntity user = repository.findByIdAndIsDeletedFalse(userEntity.getId());
            if (passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
                String encryptedPassword = passwordEncoder.encode(changePasswordRequest.getNewPassword());
                user.setPassword(encryptedPassword);
                repository.save(user);
                userResponse.setSuccess(true);
            } else {
                userResponse.setSuccess(false);
                userResponse.setCode(1004);
                userResponse.setMessage("Old password incorrect");
            }
        }
        return userResponse;
    }



    public UserResponse resetPassword(String email) {
        UserResponse userResponse = new UserResponse();
        UserEntity userEntity = repository.findUserByEmail(email);
        if (userEntity != null) {
        } else {
            userResponse.setSuccess(false);
            userResponse.setMessage("User can't found");
        }
        return userResponse;
    }



    void populateUserDTO(UserDTO userDTO, UserEntity userEntity) {
        userDTO.setId(userEntity.getId());
        userDTO.setAvatarUrl(userEntity.getAvatarUrl());
        userDTO.setBirthday(userEntity.getBirthday());
        userDTO.setQrCode(userEntity.getQrCode());
        userDTO.setName(userEntity.getName());
        userDTO.setPhoneNumber(userEntity.getPhoneNumber());
        userDTO.setSex(userEntity.getSex());
        userDTO.setType(UserType.APP_USER.name());
        userDTO.setCityId(userEntity.getCityId());
        userDTO.setDistrictId(userEntity.getDistrictId());
        userDTO.setStoreId(userEntity.getStoreId());
        userDTO.setAddress(userEntity.getAddress());
        userDTO.setUserName(userEntity.getUserName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setNumberOfPoints(userEntity.getNumberOfPoints());
        userDTO.setCustomerId(userEntity.getCustomerId());
        userDTO.setFuriganaName(userEntity.getFuriganaName());
        userDTO.setBackgroundDisease(userEntity.getBackgroundDisease());

        if (userEntity.getType() == null) {
            userDTO.setCustomerType(CustomerType.MEMBER_APP.name());
        } else {
            userDTO.setCustomerType(userEntity.getType());
        }
    }



    public UserResponse updateProfileUserApp(UserRequest userRequest) {
        UserResponse userResponse = new UserResponse();
        UserEntity userEntity = getCurrentUser().getUserEntity();
        UserResponse Invalid_phone_number = checkInvalidPhoneAndFurigana(userRequest);
        if (Invalid_phone_number != null) return Invalid_phone_number;
        if (userEntity != null) {
            List<String> phoneNumberExists = repository.findPhoneNumberExist(userRequest.getPhoneNumber(), userEntity.getId());
            if (!phoneNumberExists.isEmpty()) {
                return new UserResponse("This phone number already exists", 4008);
            }
            updateUserApp(userRequest, userResponse, userEntity);
        } else {
            userResponse.setSuccess(false);
            userResponse.setCode(1004);
            userResponse.setMessage("Old password incorrect");
        }
        return userResponse;
    }

    public UserResponse updateUserByAdmin(UserRequest userRequest) {
        UserResponse userResponse = new UserResponse();
        UserAdmin userAdmin = getCurrentUser().getUserAdmin();
        if (userAdmin == null) {
            return new UserResponse("This account is not authorized", 4001);
        }
        UserEntity userEntity = repository.findCustomerById(userRequest.getId());
        if (userEntity == null) {
            return new UserResponse("User not found", 4004);
        }
        if (!userRequest.getPhoneNumber().equals("")) {
            UserResponse Invalid_phone_number = checkInvalidPhoneAndFurigana(userRequest);
            if (Invalid_phone_number != null) return Invalid_phone_number;
            List<String> phoneNumberExists = repository.findPhoneNumberExist(userRequest.getPhoneNumber(), userEntity.getId());
            if (!phoneNumberExists.isEmpty()) {
                return new UserResponse("This phone number already exists", 4008);
            }
        }
        updateUserApp(userRequest, userResponse, userEntity);

        return userResponse;
    }

    private UserResponse checkInvalidPhoneAndFurigana(UserRequest userRequest) {
        boolean checkPhone = Regex.checkPhoneNumber(userRequest.getPhoneNumber());
        if (!checkPhone) {
            return new UserResponse("Invalid phone number", 4007);
        }
        boolean checkFurigana = Regex.checkFuriganaName(userRequest.getFuriganaName());
        if (!checkFurigana) {
            return new UserResponse("Invalid furigana name", 4007);
        }

        return null;
    }

    private void updateUserApp(UserRequest userRequest, UserResponse userResponse, UserEntity userEntity) {
        UserEntity user = repository.findByIdAndIsDeletedFalse(userEntity.getId());
        if (user != null) {
            UserEntity userEntityResult = repository.save(user);
            UserDTO userDTO = new UserDTO();
            populateUserDTO(userDTO, userEntityResult);
            userResponse.setUser(userDTO);
        }
    }

    @Transactional

    public byte[] getQRCodeImage(String text, int width, int height) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);

            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }






    public UserResponse registerVerificationEmail(String email) throws BaseException {
        try {
            UserEntity userEntity = repository.findUserByEmail(email);
            if (userEntity != null) {
                throw new BaseException("Email exist register", 4006);
            }
            UserResponse userResponse = new UserResponse();
            userResponse.setSuccess(true);
            userResponse.setMessage("Sent link verification success");
            userResponse.setCode(200);
            return userResponse;
        } catch (MailSendException e) {
            LOGGER.error(e.getMessage());
            throw new BaseException("Send link verification fail", 4001);
        }
    }






    public void saveUser(UserEntity userEntity) {
        repository.save(userEntity);
    }




    private Long randomNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // first not 0 digit
        sb.append(random.nextInt(9) + 1);

        // rest of 11 digits
        for (int i = 0; i < 11; i++) {
            sb.append(random.nextInt(10));
        }

        return (Long.valueOf(sb.toString()));
    }

    private Long randomId() {
        Long randomNumber = randomNumber();
        UserEntity userEntity = repository.findByCustomerId(randomNumber);
        Long randomId = 0L;
        if (userEntity == null) {
            randomId = randomNumber;
        } else {
            randomId();
        }
        return randomId;
    }





    public UserResponse setStoreFavourite(Long storeId) {
        UserResponse userResponse = new UserResponse();
        Long currentUserId = getCurrentUser().getUserEntity().getId();
        UserEntity userEntity = repository.findCustomerById(currentUserId);
        if (userEntity != null) {
            userEntity.setStoreId(storeId);
            repository.save(userEntity);
            userResponse.setCode(200);
        } else {
            userResponse.setSuccess(false);
            userResponse.setCode(4004);
            userResponse.setMessage("Can't find user");
        }
        return userResponse;
    }



    Set<Long> getAllUserIds() {
        return repository.findAllUserIds();
    }


    public UserUpdateInfoResponse checkInfoUser() {
        UserUpdateInfoResponse response = new UserUpdateInfoResponse();
        Long currentUserId = getCurrentUser().getUserEntity().getId();
        UserEntity userEntity = repository.findByIdAndIsDeletedFalse(currentUserId);
        if (userEntity == null) {
            return new UserUpdateInfoResponse("User not found", 4004);
        }
        UserUpdateInfoDTO userUpdateInfoDTO = new UserUpdateInfoDTO();
        covertUserUpdateToDTO(userEntity, userUpdateInfoDTO);
        response.setUserUpdateInfoDTO(userUpdateInfoDTO);
        response.setCode(200);
        return response;
    }

    private void covertUserUpdateToDTO(UserEntity userEntity, UserUpdateInfoDTO userUpdateInfoDTO) {
        userUpdateInfoDTO.setId(userEntity.getId());
        userUpdateInfoDTO.setName(userEntity.getName());
        userUpdateInfoDTO.setFuriganaName(userEntity.getFuriganaName());
        userUpdateInfoDTO.setPhoneNumber(userEntity.getPhoneNumber());
        userUpdateInfoDTO.setUpdatePhone(!Regex.checkPhoneNumber(userEntity.getPhoneNumber()));
    }

    public UserUpdateInfoResponse updateInfoUser(String furiganaName, String phoneNumber) {
        Long currentUserId = getCurrentUser().getUserEntity().getId();
        UserEntity userEntity = repository.findByIdAndIsDeletedFalse(currentUserId);
        if (userEntity == null) {
            return new UserUpdateInfoResponse("User not found", 4004);
        }
        UserUpdateInfoResponse response = new UserUpdateInfoResponse();
        UserUpdateInfoDTO userUpdateInfoDTO = new UserUpdateInfoDTO();
        if (furiganaName != null) {
            userEntity.setFuriganaName(furiganaName);
            boolean checkFurigana = Regex.checkFuriganaName(furiganaName);
            if (!checkFurigana) {
                return new UserUpdateInfoResponse("Invalid furigana name", 4007);
            }
        }
        if (phoneNumber != null) {
            boolean checkPhone = Regex.checkPhoneNumber(phoneNumber);
            if (!checkPhone) {
                return new UserUpdateInfoResponse("Invalid phone number", 4007);
            }
            List<String> phoneNumberExists = repository.findPhoneNumberExist(phoneNumber, currentUserId);
            if (!phoneNumberExists.isEmpty()) {
                return new UserUpdateInfoResponse("This phone number already exists", 4008);
            }
            userEntity.setPhoneNumber(phoneNumber);
        }
        update(userEntity);
        covertUserUpdateToDTO(userEntity, userUpdateInfoDTO);
        response.setUserUpdateInfoDTO(userUpdateInfoDTO);
        response.setCode(200);
        return response;
    }


    public List<UserEntity> findByUserBookingRequest(UserBookingRequest userBookingRequest) {
        List<String> phones = new ArrayList<>();
        List<String> emails = new ArrayList<>();
        userBookingRequest.getBookingRequests().forEach(bookingRequest -> {
            String phone = bookingRequest.getCustomerPhone();
            String email = bookingRequest.getEmail();
            if (phone != null) {
                phones.add(phone);
            }

            if (email != null) {
                emails.add(email);
            }
        });

        return repository.findByEmailOrPhoneIn(emails, phones);
    }

    public UserEntity updateInfoUserNonApp(BookingInfoRequest request, UserEntity userEntity, Long storeId) {
        userEntity.setActive(false);
        userEntity.setName(request.getCustomerName());
        userEntity.setPhoneNumber(request.getCustomerPhone());
        userEntity.setType(request.getCustomerType());
        userEntity.setSex(request.getCustomerSex());
        userEntity.setCityId(request.getCityId());
        userEntity.setDistrictId(request.getDistrictId());
        userEntity.setBackgroundDisease(request.getBackgroundDisease());
        userEntity.setStoreId(storeId);
        save(userEntity);
        return userEntity;
    }

}
