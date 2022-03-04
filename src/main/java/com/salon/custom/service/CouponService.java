package com.salon.custom.service;

import com.salon.base.core.BaseService;
import com.salon.base.security.CurrentUserDetailsContainer;
import com.salon.custom.dto.coupon.CouponDTO;
import com.salon.custom.dto.coupon.CouponRequest;
import com.salon.custom.dto.coupon.CouponResponse;
import com.salon.custom.dto.store.StoreDTO;
import com.salon.custom.entities.*;
import com.salon.custom.enums.Roles;
import com.salon.custom.repository.CouponRepository;
import com.salon.custom.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CouponService extends BaseService<CouponEntity, CouponRepository> {

    @Autowired
    private StoreService storeService;

    @Autowired
    private CurrentUserDetailsContainer userDetailsContainer;

    @Autowired
    private UserService userService;

    // store id = 0 is coupon for all store
    private final long ALL_STORE_ID = 0L;


    private void populateCoupon(CouponResponse couponResponse, Page<CouponEntity> couponEntities) {
        if (!couponEntities.isEmpty()) {
        }
        List<CouponDTO> couponDTOS = new ArrayList<>();
        Set<Long> storeIds = new HashSet<>();
        for (CouponEntity couponEntity : couponEntities.getContent()) {
            if (couponEntity.getStoreId() != null) {
                storeIds.add(couponEntity.getStoreId());
            }
        }
        Map<Long, StoreDTO> mapStore = new HashMap<>();
        if (storeIds.size() > 0) {
            List<StoreEntity> storeEntities = storeService.findByIdIn(storeIds);
            for (StoreEntity storeEntity : storeEntities) {
                StoreDTO storeDTO = new StoreDTO();
                storeDTO.setId(storeEntity.getId());
                storeDTO.setName(storeEntity.getName());
                storeDTO.setPhoneNumber(storeEntity.getPhone());
                storeDTO.setOpenTime(storeEntity.getOpenTime());
                storeDTO.setCloseTime(storeEntity.getCloseTime());
                mapStore.put(storeDTO.getId(), storeDTO);
            }
        }
        for (CouponEntity couponEntity : couponEntities.getContent()) {
            CouponDTO couponDTO = toDto(couponEntity);
            if (couponEntity.getStoreId() != null) {
                couponDTO.setStore(mapStore.get(couponEntity.getStoreId()));
            }
            couponDTOS.add(couponDTO);
        }
        couponResponse.setPageDto(populatePageDto(couponEntities));
        couponResponse.setCouponDTOList(couponDTOS);
    }


    public CouponResponse getAllCouponForApp(Pageable pageable) {
        CouponResponse couponResponse = new CouponResponse();
        Date dateNow = new Date();
        Page<CouponEntity> couponEntities = repository.findAllEndDateGreatThanDateNow(dateNow, pageable);
        populateCoupon(couponResponse, couponEntities);
        return couponResponse;
    }


    public CouponResponse getAllCouponForUser(Pageable pageable) {
        CouponResponse couponResponse = new CouponResponse();
        Date dateNow = new Date();
        List<Long> storeIds = new ArrayList<>();
        if (userDetailsContainer.getUserDetails() != null && userDetailsContainer.getUserDetails().getUserEntity() != null) {
            UserEntity currentUser = userDetailsContainer.getUserDetails().getUserEntity();
            UserEntity userEntity = userService.findByIdAndIsDeletedFalse(currentUser.getId());
            storeIds.add(ALL_STORE_ID);
            storeIds.add(userEntity.getStoreId());
        } else {
            storeIds.add(ALL_STORE_ID);
        }

        List<CouponDTO> couponDTOS = new ArrayList<>();
        Page<CouponEntity> couponEntities = repository.findAllCouponByDate(dateNow, storeIds, pageable);
        List<StoreEntity> storeEntities = storeService.getListStoreByIds(storeIds);
        Map<Long, StoreEntity> storeEntityMap = new HashMap<>();
        for (StoreEntity storeEntity : storeEntities) {
            storeEntityMap.put(storeEntity.getId(), storeEntity);
        }
        for (CouponEntity couponEntity : couponEntities.getContent()) {
            getCouponDTO(couponDTOS, storeEntityMap, couponEntity);
        }
        couponResponse.setCouponDTOList(couponDTOS);
        couponResponse.setPageDto(populatePageDto(couponEntities));
        return couponResponse;
    }


    public List<CouponDTO> getAllCouponForUser(Long storeId, Long storeFavouriteId) {
        Date dateNow = new Date();
        List<Long> storeIds = new ArrayList<>();

        storeIds.add(ALL_STORE_ID);
        if (storeId.equals(storeFavouriteId)) {
            storeIds.add(storeId);
        }

        List<CouponDTO> couponDTOS = new ArrayList<>();
        List<CouponEntity> couponEntities = repository.findAllCouponByDate(dateNow, storeIds);
        List<StoreEntity> storeEntities = storeService.getListStoreByIds(storeIds);
        Map<Long, StoreEntity> storeEntityMap = new HashMap<>();
        for (StoreEntity storeEntity : storeEntities) {
            storeEntityMap.put(storeEntity.getId(), storeEntity);
        }
        for (CouponEntity couponEntity : couponEntities) {
            getCouponDTO(couponDTOS, storeEntityMap, couponEntity);
        }
        return couponDTOS;
    }

    private void getCouponDTO(List<CouponDTO> couponDTOS, Map<Long, StoreEntity> storeEntityMap, CouponEntity couponEntity) {
        CouponDTO couponDTO = toDto(couponEntity);
        if (couponEntity.getStoreId() == ALL_STORE_ID || couponEntity.getStoreId() == null) {
            couponDTO.setStoreName(Constant.COUPON_ALL_STORE);
        } else {
            StoreEntity storeEntity = storeEntityMap.get(couponEntity.getStoreId());
            couponDTO.setStoreName(storeEntity.getName());
        }
        couponDTOS.add(couponDTO);
    }


    /*private CouponResponse findCouponUserApp(Pageable pageable, CouponResponse couponResponse, Date dateNow, List<Long> storeIds) {
        List<CouponDTO> couponDTOS = new ArrayList<>();
        Page<CouponEntity> couponEntities = repository.findAllCouponByDate(dateNow, storeIds, pageable);
        List<StoreEntity> storeEntities = storeService.getListStoreByIds(storeIds);
        Map<Long, StoreEntity> storeEntityMap = new HashMap<>();
        for (StoreEntity storeEntity : storeEntities){
            storeEntityMap.put(storeEntity.getId(), storeEntity);
        }
        for (CouponEntity couponEntity : couponEntities.getContent()) {
            CouponDTO couponDTO = toDto(couponEntity);
            if (couponEntity.getStoreId() == ALL_STORE_ID || couponEntity.getStoreId() == null){
                couponDTO.setStoreName("全店舗");
            }else {
                StoreEntity storeEntity = storeEntityMap.get(couponEntity.getStoreId());
                couponDTO.setStoreName(storeEntity.getName());
            }
            couponDTOS.add(couponDTO);
        }
        couponResponse.setCouponDTOList(couponDTOS);
        couponResponse.setPageDto(populatePageDto(couponEntities));
        return couponResponse;
    }*/


    public CouponResponse getAllCouponForAdmin(UserAdmin userAdmin, Pageable pageable, Long storeId) {
        CouponResponse couponResponse = new CouponResponse();
        if (userAdmin != null && userAdmin.getType().equals(Roles.SYSTEM_ADMIN.getName())) {
            Page<CouponEntity> couponEntities;
            if (storeId == null) {
                couponEntities = repository.findAllByIsDeletedFalseOrderByIdDesc(pageable);
            } else {
                couponEntities = repository.findAllForAdmin(storeId, pageable);
            }
            populateCoupon(couponResponse, couponEntities);
        } else if (userAdmin != null && userAdmin.getType().equals(Roles.STORE_ADMIN.getName())) {
            Long storeIdOwner = storeService.findByOwnerId(userAdmin.getId());
            List<Long> storeIds = new ArrayList<>();
            storeIds.add(ALL_STORE_ID);
            storeIds.add(storeIdOwner);
            Page<CouponEntity> couponEntities = repository.findAllForStoreAdmin(storeIds, pageable);
            populateCoupon(couponResponse, couponEntities);
        } else {
            return couponResponse;
        }
        return couponResponse;
    }


    @Transactional
    public CouponResponse createCoupon(UserAdmin userAdmin, CouponRequest couponRequest) {
        CouponResponse couponResponse = new CouponResponse();
        if (userAdmin != null) {
            CouponEntity couponEntity = new CouponEntity();
            toEntity(couponEntity, couponRequest);
            couponEntity.setId(null);
            setStoreId(userAdmin, couponRequest, couponEntity);
            preSave(couponEntity);
            CouponEntity couponEntityResult = repository.save(couponEntity);
            couponResponse.setCouponDTO(toDto(couponEntityResult));
        }
        return couponResponse;
    }

    @Transactional
    public CouponResponse updateCoupon(UserAdmin userAdmin, CouponRequest couponRequest) {
        CouponResponse couponResponse = new CouponResponse();
        if (userAdmin != null) {
            CouponEntity couponEntity = repository.findByIdAndIsDeletedFalse(couponRequest.getId());
            if (couponEntity != null) {
                toEntity(couponEntity, couponRequest);
                setStoreId(userAdmin, couponRequest, couponEntity);
                update(couponEntity);
                CouponDTO couponDTO = toDto(couponEntity);
                String storeName = storeService.getStoreNameById(couponEntity.getStoreId());
                couponDTO.setStoreName(storeName);
                couponResponse.setCouponDTO(couponDTO);
            }
        }

        return couponResponse;
    }

    private void setStoreId(UserAdmin userAdmin, CouponRequest couponRequest, CouponEntity couponEntity) {
        Long storeId;
        if (Roles.STORE_ADMIN.getName().equals(userAdmin.getType())) {
            storeId = storeService.findByOwnerId(userAdmin.getId());
        } else {
            storeId = couponRequest.getStoreId();
        }
        if (storeId == null) {
            storeId = ALL_STORE_ID;
        }
        couponEntity.setStoreId(storeId);
    }

    @Transactional
    public CouponResponse deleteCoupon(Long couponId) {
        CouponResponse couponResponse = new CouponResponse();
        CouponEntity couponEntity = repository.findByIdAndIsDeletedFalse(couponId);
        if (couponEntity == null) {
            return new CouponResponse("Coupon not exist", 4004);
        }
        couponEntity.setDeleted(true);
        update(couponEntity);
        return couponResponse;
    }

    private void toEntity(CouponEntity couponEntity, CouponRequest couponRequest) {
        couponEntity.setDeleted(false);
        couponEntity.setTitle(couponRequest.getTitle());
        couponEntity.setDescription(couponRequest.getDescription());
        couponEntity.setImageUrl(couponRequest.getImageUrl());
        couponEntity.setStartDate(couponRequest.getStartDate());
        couponEntity.setEndDate(couponRequest.getEndDate());
        couponEntity.setPoint(couponRequest.getPoint());
    }

    private CouponDTO toDto(CouponEntity couponEntity) {
        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setStoreId(couponEntity.getStoreId());
        couponDTO.setId(couponEntity.getId());
        couponDTO.setTitle(couponEntity.getTitle());
        couponDTO.setImageUrl(couponEntity.getImageUrl());
        couponDTO.setDescription(couponEntity.getDescription());
        couponDTO.setStartDate(couponEntity.getStartDate());
        couponDTO.setEndDate(couponEntity.getEndDate());
        couponDTO.setPoint(couponEntity.getPoint());
        return couponDTO;
    }


    public CouponResponse updateStoreIdForCouponAll() {
        List<CouponEntity> couponEntities = repository.findAllByIsDeletedFalse();
        for (CouponEntity couponEntity : couponEntities) {
            if (couponEntity.getStoreId() == null) {
                couponEntity.setStoreId(ALL_STORE_ID);
            }
        }
        repository.saveAll(couponEntities);
        CouponResponse couponResponse = new CouponResponse();
        couponResponse.setCode(200);
        return couponResponse;
    }

    public CouponDTO getCouponById(Long id) {
        CouponEntity couponEntity = repository.findByIdAndIsDeletedFalse(id);
        if (couponEntity == null) return null;
        return toDto(couponEntity);
    }

    public List<CouponDTO> getCouponByIdIn(List<Long> couponIds) {
        return repository.findByIdIn(couponIds).stream().map(this::toDto).collect(Collectors.toList());
    }
}
