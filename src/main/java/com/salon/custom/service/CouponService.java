/*
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
public class CouponService extends BaseService<Coupon, CouponRepository> {

    @Autowired
    private StoreService storeService;

    @Autowired
    private CurrentUserDetailsContainer userDetailsContainer;

    @Autowired
    private UserService userService;

    // store id = 0 is coupon for all store
    private final long ALL_STORE_ID = 0L;


    private void populateCoupon(CouponResponse couponResponse, Page<Coupon> couponEntities) {
        if (!couponEntities.isEmpty()) {
        }
        List<CouponDTO> couponDTOS = new ArrayList<>();
        Set<Long> storeIds = new HashSet<>();
        */
/*for (Coupon coupon : couponEntities.getContent()) {
            if (coupon.getStoreId() != null) {
                storeIds.add(coupon.getStoreId());
            }
        }*//*

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
        for (Coupon coupon : couponEntities.getContent()) {
            CouponDTO couponDTO = toDto(coupon);
            if (coupon.getStoreId() != null) {
                couponDTO.setStore(mapStore.get(coupon.getStoreId()));
            }
            couponDTOS.add(couponDTO);
        }
        couponResponse.setPageDto(populatePageDto(couponEntities));
        couponResponse.setCouponDTOList(couponDTOS);
    }


    public CouponResponse getAllCouponForApp(Pageable pageable) {
        CouponResponse couponResponse = new CouponResponse();
        Date dateNow = new Date();
        Page<Coupon> couponEntities = repository.findAllEndDateGreatThanDateNow(dateNow, pageable);
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
        Page<Coupon> couponEntities = repository.findAllCouponByDate(dateNow, storeIds, pageable);
        List<StoreEntity> storeEntities = storeService.getListStoreByIds(storeIds);
        Map<Long, StoreEntity> storeEntityMap = new HashMap<>();
        for (StoreEntity storeEntity : storeEntities) {
            storeEntityMap.put(storeEntity.getId(), storeEntity);
        }
        for (Coupon coupon : couponEntities.getContent()) {
            getCouponDTO(couponDTOS, storeEntityMap, coupon);
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
        List<Coupon> couponEntities = repository.findAllCouponByDate(dateNow, storeIds);
        List<StoreEntity> storeEntities = storeService.getListStoreByIds(storeIds);
        Map<Long, StoreEntity> storeEntityMap = new HashMap<>();
        for (StoreEntity storeEntity : storeEntities) {
            storeEntityMap.put(storeEntity.getId(), storeEntity);
        }
        for (Coupon coupon : couponEntities) {
            getCouponDTO(couponDTOS, storeEntityMap, coupon);
        }
        return couponDTOS;
    }

    private void getCouponDTO(List<CouponDTO> couponDTOS, Map<Long, StoreEntity> storeEntityMap, Coupon coupon) {
        CouponDTO couponDTO = toDto(coupon);
        if (coupon.getStoreId() == ALL_STORE_ID || coupon.getStoreId() == null) {
            couponDTO.setStoreName(Constant.COUPON_ALL_STORE);
        } else {
            StoreEntity storeEntity = storeEntityMap.get(coupon.getStoreId());
            couponDTO.setStoreName(storeEntity.getName());
        }
        couponDTOS.add(couponDTO);
    }


    */
/*private CouponResponse findCouponUserApp(Pageable pageable, CouponResponse couponResponse, Date dateNow, List<Long> storeIds) {
        List<CouponDTO> couponDTOS = new ArrayList<>();
        Page<Coupon> couponEntities = repository.findAllCouponByDate(dateNow, storeIds, pageable);
        List<StoreEntity> storeEntities = storeService.getListStoreByIds(storeIds);
        Map<Long, StoreEntity> storeEntityMap = new HashMap<>();
        for (StoreEntity storeEntity : storeEntities){
            storeEntityMap.put(storeEntity.getId(), storeEntity);
        }
        for (Coupon couponEntity : couponEntities.getContent()) {
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
    }*//*



    public CouponResponse getAllCouponForAdmin(UserAdmin userAdmin, Pageable pageable, Long storeId) {
        CouponResponse couponResponse = new CouponResponse();
        if (userAdmin != null && userAdmin.getType().equals(Roles.SYSTEM_ADMIN.getName())) {
            Page<Coupon> couponEntities;
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
            Page<Coupon> couponEntities = repository.findAllForStoreAdmin(storeIds, pageable);
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
            Coupon coupon = new Coupon();
            toEntity(coupon, couponRequest);
            coupon.setId(null);
            setStoreId(userAdmin, couponRequest, coupon);
            preSave(coupon);
            Coupon couponResult = repository.save(coupon);
            couponResponse.setCouponDTO(toDto(couponResult));
        }
        return couponResponse;
    }

    @Transactional
    public CouponResponse updateCoupon(UserAdmin userAdmin, CouponRequest couponRequest) {
        CouponResponse couponResponse = new CouponResponse();
        if (userAdmin != null) {
            Coupon coupon = repository.findByIdAndIsDeletedFalse(couponRequest.getId());
            if (coupon != null) {
                toEntity(coupon, couponRequest);
                setStoreId(userAdmin, couponRequest, coupon);
                update(coupon);
                CouponDTO couponDTO = toDto(coupon);
                String storeName = storeService.getStoreNameById(coupon.getStoreId());
                couponDTO.setStoreName(storeName);
                couponResponse.setCouponDTO(couponDTO);
            }
        }

        return couponResponse;
    }

    private void setStoreId(UserAdmin userAdmin, CouponRequest couponRequest, Coupon coupon) {
        Long storeId;
        if (Roles.STORE_ADMIN.getName().equals(userAdmin.getType())) {
            storeId = storeService.findByOwnerId(userAdmin.getId());
        } else {
            storeId = couponRequest.getStoreId();
        }
        if (storeId == null) {
            storeId = ALL_STORE_ID;
        }
        coupon.setStoreId(storeId);
    }

    @Transactional
    public CouponResponse deleteCoupon(Long couponId) {
        CouponResponse couponResponse = new CouponResponse();
        Coupon coupon = repository.findByIdAndIsDeletedFalse(couponId);
        if (coupon == null) {
            return new CouponResponse("Coupon not exist", 4004);
        }
        coupon.setDeleted(true);
        update(coupon);
        return couponResponse;
    }

    private void toEntity(Coupon coupon, CouponRequest couponRequest) {
        coupon.setDeleted(false);
        coupon.setTitle(couponRequest.getTitle());
        coupon.setDescription(couponRequest.getDescription());
        coupon.setImageUrl(couponRequest.getImageUrl());
        coupon.setStartDate(couponRequest.getStartDate());
        coupon.setEndDate(couponRequest.getEndDate());
        coupon.setPoint(couponRequest.getPoint());
    }

    private CouponDTO toDto(Coupon coupon) {
        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setStoreId(coupon.getStoreId());
        couponDTO.setId(coupon.getId());
        couponDTO.setTitle(coupon.getTitle());
        couponDTO.setImageUrl(coupon.getImageUrl());
        couponDTO.setDescription(coupon.getDescription());
        couponDTO.setStartDate(coupon.getStartDate());
        couponDTO.setEndDate(coupon.getEndDate());
        couponDTO.setPoint(coupon.getPoint());
        return couponDTO;
    }


    public CouponResponse updateStoreIdForCouponAll() {
        List<Coupon> couponEntities = repository.findAllByIsDeletedFalse();
        for (Coupon coupon : couponEntities) {
            if (coupon.getStoreId() == null) {
                coupon.setStoreId(ALL_STORE_ID);
            }
        }
        repository.saveAll(couponEntities);
        CouponResponse couponResponse = new CouponResponse();
        couponResponse.setCode(200);
        return couponResponse;
    }

    public CouponDTO getCouponById(Long id) {
        Coupon coupon = repository.findByIdAndIsDeletedFalse(id);
        if (coupon == null) return null;
        return toDto(coupon);
    }

    public List<CouponDTO> getCouponByIdIn(List<Long> couponIds) {
        return repository.findByIdIn(couponIds).stream().map(this::toDto).collect(Collectors.toList());
    }
}
*/
