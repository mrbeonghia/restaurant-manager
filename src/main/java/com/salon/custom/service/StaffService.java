package com.salon.custom.service;

import com.salon.base.core.BaseService;
import com.salon.base.security.CurrentUserDetailsContainer;
import com.salon.custom.dto.staff.StaffDTO;
import com.salon.custom.dto.staff.StaffRequest;
import com.salon.custom.dto.staff.StaffResponse;
import com.salon.custom.dto.staff.schedule.ShiftRequest;
import com.salon.custom.dto.staff.staff_user.StaffSexDTO;
import com.salon.custom.dto.staff.staff_user.StaffUserDTO;
import com.salon.custom.dto.staff.staff_user.StaffUserRequest;
import com.salon.custom.dto.staff.staff_user.StaffUserResponse;
import com.salon.custom.entities.StaffEntity;
import com.salon.custom.entities.UserAdmin;
import com.salon.custom.enums.Roles;
import com.salon.custom.regex.Regex;
import com.salon.custom.repository.StaffRepository;
import com.salon.custom.security.role.annotation.IsStaffUser;
import com.salon.custom.security.role.annotation.IsSystemUser;
import com.salon.custom.service.authentication.AuthenticationEventService;
import com.salon.custom.service.authentication.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.salon.custom.util.Constant.LENGTH_PWD_STAFF_DEFAULT;
import static com.salon.custom.util.Constant.SEXES;
import static com.salon.custom.util.DateUtils.convertEndTimeWorkStore;
import static com.salon.custom.util.DateUtils.convertToDate;


@Service
@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
@Slf4j
public class StaffService extends BaseService<StaffEntity, StaffRepository> {
    private static final Logger LOGGER = LoggerFactory.getLogger(StaffService.class);

    @Autowired
    private BookingService bookingService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationEventService authenticationEventService;

    @Autowired
    private CurrentUserDetailsContainer userDetailsContainer;

    @Autowired
    private StoreService storeService;

    @Autowired
    private UserAdminService userAdminService;



    public List<StaffDTO> findByIdIn(Set<Long> staffIds) {
        return repository.findByIdIn(staffIds);
    }

    public List<StaffEntity> findAllStaffs() {
        return repository.findAllByIsDeletedFalse();
    }

    public StaffDTO findByStaffId(Long staffId) {
        return repository.findByStaffId(staffId);
    }

    public StaffEntity findStaffEntityById(Long staffId) {
        return repository.findByIdAndIsDeletedFalse(staffId);
    }

    public List<StaffEntity> findStaffsByIds(Set<Long> staffIds) {
        return repository.findByIdInAndIsDeletedFalse(staffIds);
    }

    /*@Transactional
    public StaffResponse updateStaff(StaffRequest staffRequest) {
        StaffResponse staffResponse = new StaffResponse();
        UserAdmin userAdmin = userDetailsContainer.getUserDetails().getUserAdmin();
        if (userAdmin != null) {
            StaffEntity staffEntity = repository.findByIdAndIsDeletedFalse(staffRequest.getStaffId());
            if (staffEntity != null) {
                toEntity(staffEntity, staffRequest);
                if (Roles.STORE_ADMIN.getName().equals(userAdmin.getType())) {
                    staffEntity.setStoreId(storeService.findByOwnerId(userAdmin.getId()));
                }
                StaffEntity staffEntityUpdate = update(staffEntity);
                staffResponse.setStaffDTO(popularStaff(staffEntityUpdate));
            } else {
                staffResponse.setSuccess(false);
                staffResponse.setMessage("Can't find staff");
            }
        } else {
            staffResponse.setSuccess(false);
            staffResponse.setMessage("User can't update staff");
        }

        return staffResponse;
    }*/

    private void toEntity(StaffEntity staffEntity, StaffRequest staffRequest) {
        staffEntity.setAvatarUrl(staffRequest.getAvatarUrl());
        staffEntity.setBirthday(staffRequest.getBirthday());
        staffEntity.setName(staffRequest.getName());
        staffEntity.setEmail(staffRequest.getEmail());
        staffEntity.setPhoneNumber(staffRequest.getPhoneNumber());
        staffEntity.setSex(staffRequest.getSex());
        staffEntity.setSelfIntroduction(staffRequest.getSelfIntroduction());
        staffEntity.setIsActive(true);

    }

    private String setPasswordDefault(String phoneNumber) {
        String passwordDefault = "";
        if (phoneNumber.length() > LENGTH_PWD_STAFF_DEFAULT) {
            passwordDefault = phoneNumber.substring(phoneNumber.length() - LENGTH_PWD_STAFF_DEFAULT);
        }
        return passwordDefault;
    }


    @Transactional
    public StaffResponse createStaffBySystemAdmin(StaffRequest staffRequest) {
        StaffResponse staffResponse = new StaffResponse();
        UserAdmin userAdmin = userDetailsContainer.getUserDetails().getUserAdmin();
        StaffResponse userCanNotCreateStaff = validateCreateStaff(staffRequest, userAdmin);
        if (userCanNotCreateStaff != null) return userCanNotCreateStaff;


        StaffEntity staffEntity = new StaffEntity();
        toEntity(staffEntity, staffRequest);
        staffEntity.setPassword(passwordEncoder.encode(setPasswordDefault(staffEntity.getPhoneNumber())));
        staffEntity.setPasswordEncode(userAdminService.encodePasswordCleaner(
                setPasswordDefault(staffEntity.getPhoneNumber())));

        return staffResponse;
    }


    @Transactional
    public StaffResponse createStaffByStoreAdmin(StaffRequest staffRequest) {
        UserAdmin userAdmin = userDetailsContainer.getUserDetails().getUserAdmin();
        if (userAdmin == null || !Roles.STORE_ADMIN.getName().equals(userAdmin.getType())) {
            return new StaffResponse("This account is not authorized", 4001);
        }

        StaffEntity staffByPhone = repository.findByPhoneNumber(staffRequest.getPhoneNumber());
        Long storeId = userAdmin.getStoreId();
        Set<Long> storeIds = new HashSet<>();
        storeIds.add(storeId);

        return new StaffResponse();
    }


    private StaffResponse validateCreateStaff(StaffRequest staffRequest, UserAdmin userAdmin) {
        if (userAdmin == null || !(Roles.SYSTEM_ADMIN.getName().equals(userAdmin.getType()))) {
            return new StaffResponse("This account is not authorized", 4001);
        }
        StaffEntity staffByPhone = repository.findByPhoneNumber(staffRequest.getPhoneNumber());
        if (staffByPhone != null) {
            return new StaffResponse("This staff phone already exist", 4003);
        }
        boolean checkPhone = Regex.checkPhoneNumber(staffRequest.getPhoneNumber());
        if (!checkPhone) {
            return new StaffResponse("Invalid phone number", 4007);
        }
        /*StaffEntity staffByEmail = repository.findByEmailAndIsDeletedFalse(staffRequest.getEmail());
        if (staffByEmail != null) {
            return new StaffResponse("This staff email already exist", 4103);
        }*/
        return null;
    }


    @Transactional
    public StaffResponse updateStaffByAdmin(StaffRequest staffRequest) {
        UserAdmin userAdmin = userDetailsContainer.getUserDetails().getUserAdmin();
        StaffResponse staffResponse = new StaffResponse();
        StaffEntity staffEntity = repository.findByIdAndIsDeletedFalse(staffRequest.getStaffId());
        StaffResponse checkStaffExits = validateStaffWhenUpdate(userAdmin, staffRequest, staffEntity);
        if (checkStaffExits != null) return checkStaffExits;
        Long staffId = staffRequest.getStaffId();
        toEntity(staffEntity, staffRequest);
        Set<Long> storeIds = staffRequest.getStoreIds();


        return staffResponse;
    }


    private StaffResponse validateStaffWhenUpdate(UserAdmin userAdmin, StaffRequest staffRequest,
                                                  StaffEntity staffEntity) {
        if (userAdmin == null) {
            return new StaffResponse("This account is not authorized", 4001);
        }
        if (staffEntity == null) {
            return new StaffResponse("This staff not found", 4004);
        }
        StaffEntity staffByPhone = repository.findByPhoneNumber(staffRequest.getPhoneNumber());
        if (staffByPhone != null && !(staffByPhone.equals(staffEntity))) {
            return new StaffResponse("This staff phone already exist", 4003);
        }
        /*boolean checkPhone = Regex.checkPhoneNumber(staffRequest.getPhoneNumber());
        if (!checkPhone) {
            return new StaffResponse("Invalid phone number", 4007);
        }*/
        /*StaffEntity staffByEmail = repository.findByEmailAndIsDeletedFalse(staffRequest.getEmail());
        if (staffByEmail != null && !(staffByEmail.equals(staffEntity))) {
            return new StaffResponse("This staff email already exist", 4103);
        }*/
        return null;
    }






    public StaffUserResponse getProfileStaffUser() {
        StaffEntity staffEntityOld = getCurrentUser().getStaffEntity();
        StaffEntity staffEntity = repository.findByIdAndIsDeletedFalse(staffEntityOld.getId());
        if (staffEntity == null) {
            return new StaffUserResponse("This account is not authorized", 4001);
        }
        StaffUserDTO staffUserDTO = covertStaffUserToDTO(staffEntity);
        return new StaffUserResponse(staffUserDTO);
    }


    @Transactional
    public StaffUserResponse updateProfileByStaff(StaffUserRequest staffUserRequest) {
        Long staffId = getCurrentUser().getStaffEntity().getId();
        StaffEntity staffEntity = repository.findByIdAndIsDeletedFalse(staffId);
        if (staffEntity == null) {
            return new StaffUserResponse("This account is not authorized", 4001);
        }
        StaffEntity staffByPhone = repository.findByPhoneNumber(staffUserRequest.getPhoneNumber());
        if (staffByPhone != null && !staffByPhone.getId().equals(staffEntity.getId())) {
            return new StaffUserResponse("This phone number already exist", 4008);
        }
        boolean checkPhone = Regex.checkPhoneNumber(staffUserRequest.getPhoneNumber());
        if (!checkPhone) {
            return new StaffUserResponse("Invalid phone number", 4007);
        }
        covertStaffUserRequestToEntity(staffUserRequest, staffEntity);
        String oldPassword = staffUserRequest.getOldPassword();
        String newPassword = staffUserRequest.getNewPassword();
        if (oldPassword != null && !oldPassword.equals("") && newPassword != null && !newPassword.equals("")) {
            if (!passwordEncoder.matches(oldPassword, staffEntity.getPassword())) {
                return new StaffUserResponse("Old password incorrect", 4101);
            }
            String encryptPassword = passwordEncoder.encode(newPassword);
            staffEntity.setPassword(encryptPassword);
            staffEntity.setPasswordEncode(userAdminService.encodePasswordCleaner(newPassword));
        }
        repository.save(staffEntity);
        StaffUserDTO staffUserDTO = covertStaffUserToDTO(staffEntity);
        return new StaffUserResponse(staffUserDTO);
    }


    private StaffDTO popularStaff(StaffEntity staffEntity) {
        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setId(staffEntity.getId());
        staffDTO.setAvatarUrl(staffEntity.getAvatarUrl());
        staffDTO.setBirthday(staffEntity.getBirthday());
        staffDTO.setPhoneNumber(staffEntity.getPhoneNumber());
        staffDTO.setName(staffEntity.getName());
        staffDTO.setEmail(staffEntity.getEmail());
        staffDTO.setSex(staffEntity.getSex());
        staffDTO.setSelfIntroduction(staffEntity.getSelfIntroduction());
        staffDTO.setRatingAverage(staffEntity.getRatingAverage());
        return staffDTO;
    }


    private void covertStaffUserRequestToEntity(StaffUserRequest staffUserRequest, StaffEntity staffEntity) {
        staffEntity.setName(staffUserRequest.getName());
        staffEntity.setPhoneNumber(staffUserRequest.getPhoneNumber());
        staffEntity.setSex(staffUserRequest.getSex());
        /*Set<Long> comboPackIds = staffUserRequest.getComboPackIds();
        if (!comboPackIds.isEmpty()){
            List<StaffComboEntity> staffComboEntities = new ArrayList<>();
            for (Long comboPackId : comboPackIds){
                StaffComboEntity staffComboEntity = new StaffComboEntity();
                staffComboEntity.setStaffId(staffEntity.getId());
                staffComboEntity.setComboPackId(comboPackId);
                staffComboEntities.add(staffComboEntity);
                staffComboService.preSave(staffComboEntity);
            }
            staffComboService.saveAll(staffComboEntities);
        }*/
    }

    private StaffUserDTO covertStaffUserToDTO(StaffEntity staffEntity) {
        StaffUserDTO staffUserDTO = new StaffUserDTO();
        staffUserDTO.setId(staffEntity.getId());
        staffUserDTO.setName(staffEntity.getName());
        staffUserDTO.setPhoneNumber(staffEntity.getPhoneNumber());
        staffUserDTO.setSex(staffEntity.getSex());
        staffUserDTO.setAvatarUrl(staffEntity.getAvatarUrl());
        return staffUserDTO;
    }


    /*private void staffInStoreToEntity(StaffInStore staffInStore, StaffRequest staffRequest) {
        staffInStore.setMonday(staffRequest.getMonday());
        staffInStore.setTuesday(staffRequest.getTuesday());
        staffInStore.setWednesday(staffRequest.getWednesday());
        staffInStore.setThursday(staffRequest.getThursday());
        staffInStore.setFriday(staffRequest.getFriday());
        staffInStore.setSaturday(staffRequest.getFriday());
        staffInStore.setSunday(staffRequest.getSunday());
    }*/


    public StaffResponse getStaffForUserApp(Long storeId, Date date, String time, Long packageCareId, Long orderId) {
        LOGGER.info("StoreId: {}, Date: {}, Time: {}, PackageId: {}, OrderId: {}", storeId, date, time, packageCareId, orderId);
        StaffResponse staffResponse = new StaffResponse();
/*        PackageCare packageCare = packageCareService.findByIdAndIsDeletedFalse(packageCareId);
        if (packageCare != null) {
            UserAdmin userAdmin = userDetailsContainer.getUserDetails().getUserAdmin();
            if (userAdmin != null && Roles.STORE_ADMIN.getName().equals(userAdmin.getType())) {
                storeId = storeService.findByOwnerId(userAdmin.getId());
            }
            String day = DateUtils.getDayOfWeek(date);
            List<StaffEntity> staffEntities = repository.findByStaffInStore(storeId);
            List<Long> staffIds = calendarOrderService.getStaffWorkMatchTimeAndStoreId(time, date, storeId, packageCare.getTime(), orderId);

            List<StaffDTO> staffDTOList = new ArrayList<>();
            for (StaffEntity staffEntity : staffEntities) {
                String timeDayRequest = timeRequest(day, staffEntity);
                if (!"".equals(timeDayRequest) && timeDayRequest != null) {
                    String[] timeArray = timeDayRequest.split(";");
                    Date dateStaffStart = DateUtils.convertToDate(date, timeArray[0]);
                    Date dateStaffEnd = DateUtils.convertToDate(date, timeArray[1]);
                    Date dateRequestStart = DateUtils.convertToDate(date, time);
                    Date dateRequestEnd = DateUtils.convertStringToDate(date, time, packageCare.getTime());
                    if (dateRequestEnd != null && dateStaffStart != null && dateStaffEnd != null && dateRequestStart != null
                            && (dateRequestEnd.after(dateStaffStart) || dateRequestEnd.equals(dateStaffStart))
                            && (dateRequestEnd.before(dateStaffEnd) || dateRequestEnd.equals(dateStaffEnd))
                            && (dateRequestStart.after(dateStaffStart) || dateRequestStart.equals(dateStaffStart))) {
                        if (!staffIds.contains(staffEntity.getId())) {
                            staffDTOList.add(popularStaff(staffEntity));
                        }
                    }
                }
            }

            staffResponse.setStaffs(staffDTOList);
        } else {
            staffResponse.setSuccess(false);
        }*/
        staffResponse.setCode(4004);
        staffResponse.setMessage("この機能は利用できません。");
        return staffResponse;
    }


    public StaffResponse getStaffForUserApp(Long storeId, Pageable pageable) {
        StaffResponse staffResponse = new StaffResponse();
        Page<StaffDTO> staffDTOPage = repository.getStaffForUserApp(storeId, pageable);
        staffDTOPage.getContent();
        staffResponse.setStaffs(staffDTOPage.getContent());
        staffResponse.setPageDto(populatePageDto(staffDTOPage));
        return staffResponse;
    }

    @IsSystemUser
    public StaffResponse getStaffForAdmin(Long storeId, Long staffId, String staffName, Pageable pageable) {
        StaffResponse staffResponse = new StaffResponse();
        List<StaffDTO> staffDTOS = new ArrayList<>();
        UserAdmin userAdmin = userDetailsContainer.getUserDetails().getUserAdmin();

        staffResponse.setCode(200);
        staffResponse.setStaffs(staffDTOS);
        return staffResponse;
    }



    public StaffResponse searchStaff(String name, Long storeId, Pageable pageable) {
        StaffResponse staffResponse = new StaffResponse();
        UserAdmin userAdmin = userDetailsContainer.getUserDetails().getUserAdmin();
        if (userAdmin != null && Roles.STORE_ADMIN.getName().equals(userAdmin.getType())) {
            storeId = storeService.findByOwnerId(userAdmin.getId());
        }
        Page<StaffEntity> staffEntityPage = repository.searchStaff(name, storeId, pageable);
        staffEntityPage.getContent();
        List<StaffDTO> staffDTOS = new ArrayList<>();
        for (StaffEntity staffEntity : staffEntityPage.getContent()) {
            StaffDTO staffDTO = new StaffDTO();
            staffDTO.setId(staffEntity.getId());
            staffDTO.setName(staffEntity.getName());
            staffDTO.setAvatarUrl(staffEntity.getAvatarUrl());
            staffDTOS.add(staffDTO);
        }
        staffResponse.setStaffs(staffDTOS);
        staffResponse.setPageDto(populatePageDto(staffEntityPage));
        return staffResponse;
    }


    @Transactional
    public StaffResponse deleteStaffForAdmin(Long staffId) {
        StaffResponse staffResponse = new StaffResponse();
        UserAdmin userAdmin = userDetailsContainer.getUserDetails().getUserAdmin();
        if (userAdmin == null) {
            return new StaffResponse("This account is not authorized", 4001);
        }
        StaffEntity staffEntity = repository.findByIdAndIsDeletedFalse(staffId);

        staffEntity.setDeleted(true);
        staffEntity.setIsActive(null);
        authenticationEventService.deleteAuthEvent(staffId);
        repository.save(staffEntity);

        staffResponse.setCode(200);
        return staffResponse;
    }


    public StaffEntity findStaffById(Long staffId) {
        return repository.findByIdAndIsDeletedFalse(staffId);
    }


    /*public StaffResponse updateOldStaffInStore() {
        StaffResponse staffResponse = new StaffResponse();
        UserAdmin userAdmin = userDetailsContainer.getUserDetails().getUserAdmin();
        if (userAdmin == null) {
            staffResponse.setCode(4003);
            return staffResponse;
        }
        List<StaffEntity> staffEntities = repository.findAllStaff();
        List<StaffInStore> staffInStoreList = new ArrayList<>();
        for (StaffEntity staffEntity : staffEntities) {
            StaffInStore staffInStore = new StaffInStore();
            staffInStore.setStaffId(staffEntity.getId());
            if (staffEntity.getStoreId() != null) {
                staffInStore.setStoreId(staffEntity.getStoreId());
            }
            staffInStore.setMonday(staffEntity.getMonthDay());
            staffInStore.setTuesday(staffEntity.getTueDay());
            staffInStore.setWednesday(staffEntity.getWedDay());
            staffInStore.setThursday(staffEntity.getThuDay());
            staffInStore.setFriday(staffEntity.getFriDay());
            staffInStore.setSaturday(staffEntity.getSatDay());
            staffInStore.setSunday(staffEntity.getSunDay());
            staffInStore.setCreatedTime(staffEntity.getCreatedTime());
            staffInStore.setUpdatedTime(staffEntity.getUpdatedTime());
            staffInStore.setCreatedByUserId(userAdmin.getId());
            staffInStore.setUpdatedByUserId(userAdmin.getId());
            staffInStoreList.add(staffInStore);
        }
        staffInStoreService.saveStaffInStoreSet(staffInStoreList);
        staffResponse.setCode(200);
        return staffResponse;
    }*/


    public StaffResponse getStaffByPhoneNumber(String phoneNumber) {
        StaffResponse staffResponse = new StaffResponse();
        UserAdmin userAdmin = userDetailsContainer.getUserDetails().getUserAdmin();
        if (userAdmin != null && Roles.STORE_ADMIN.getName().equals(userAdmin.getType())) {
            Long storeId = storeService.findByOwnerId(userAdmin.getId());
            StaffEntity staffEntity = repository.findByPhoneNumber(phoneNumber);
            if (staffEntity == null) {
                return new StaffResponse("This phone number not found", 4004);
            }
            Set<Long> storeIds = new HashSet<>();
            storeIds.add(storeId);
            StaffDTO staffDTO = popularStaff(staffEntity);
            staffResponse.setStaffDTO(staffDTO);
        } else {
            return new StaffResponse("This account can not find staff", 4001);
        }
        return staffResponse;
    }


    public Set<StaffEntity> findAllStaffByIds(List<Long> storeIds, List<String> staffNames) {
        return repository.findAllStaffByIds(storeIds, staffNames);
    }

    public Map<Long, String> getMapStaffName(Set<Long> staffIds) {
        List<StaffEntity> staffEntities = repository.findByIdInAndIsDeletedFalse(staffIds);
        return staffEntities.stream().collect(Collectors.toMap(StaffEntity::getId, StaffEntity::getName));
    }



    /*public boolean checkScheduleStaff(StaffRequest staffRequest, StaffEntity staffEntity, Long storeId) throws ParseException {
        List<StaffInStore> staffInStores;
        if (storeId != null) {
            staffInStores = staffInStoreService.findByAnotherStore(storeId, staffEntity.getId());
        } else {
            staffInStores = staffInStoreService.findByStaffId(staffEntity.getId());
        }
        boolean check;
        List<Boolean> listCheck = new ArrayList<>();
        for (StaffInStore staffInStore : staffInStores) {
            check = checkTimeStaff(staffInStore.getMonday(), staffRequest.getMonday());
            listCheck.add(check);
            check = checkTimeStaff(staffInStore.getTuesday(), staffRequest.getTuesday());
            listCheck.add(check);
            check = checkTimeStaff(staffInStore.getWednesday(), staffRequest.getWednesday());
            listCheck.add(check);
            check = checkTimeStaff(staffInStore.getThursday(), staffRequest.getThursday());
            listCheck.add(check);
            check = checkTimeStaff(staffInStore.getFriday(), staffRequest.getFriday());
            listCheck.add(check);
            check = checkTimeStaff(staffInStore.getSaturday(), staffRequest.getSaturday());
            listCheck.add(check);
            check = checkTimeStaff(staffInStore.getSunday(), staffRequest.getSunday());
            listCheck.add(check);
        }
        for (Boolean checkTime : listCheck) {
            if (!checkTime) {
                return false;
            }
        }
        return true;
    }*/

    public boolean checkScheduleStaff(List<ShiftRequest> shiftRequests) {
        if (shiftRequests == null) {
            return false;
        }
        for (int i = 0; i < shiftRequests.size() - 1; i++) {
            for (int j = shiftRequests.size() - 1; j > i; j--) {
                try {
                    return !checkDuplicateShiftWork(shiftRequests.get(i), shiftRequests.get(j));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    private boolean checkDuplicateShiftWork(ShiftRequest day1, ShiftRequest day2) throws ParseException {
        Date dateNow = new Date();
        long startTime1 = Objects.requireNonNull(convertToDate(dateNow, day1.getStartTime())).getTime();
        long endTime1 = Objects.requireNonNull(convertEndTimeWorkStore(dateNow, day1.getEndTime())).getTime();
        long startTime2 = Objects.requireNonNull(convertToDate(dateNow, day2.getStartTime())).getTime();
        long endTime2 = Objects.requireNonNull(convertEndTimeWorkStore(dateNow, day2.getEndTime())).getTime();
        return (startTime1 >= startTime2 || endTime1 <= startTime2) &&
                (startTime1 < startTime2 || endTime1 > endTime2) &&
                (startTime1 >= endTime2 || endTime1 <= endTime2);
    }


    private boolean checkTimeStaff(String day, String dayRequest) throws ParseException {
        if (day == null || dayRequest == null) {
            return true;
        }
        List<String> timeInDay = new ArrayList<>(Arrays.asList(day.split(";")));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        long startTime = sdf.parse(timeInDay.get(0)).getTime();
        long endTime = sdf.parse(timeInDay.get(1)).getTime();
        List<String> timeInDayRequest = new ArrayList<>(Arrays.asList(dayRequest.split(";")));
        long startTimeRequest = sdf.parse(timeInDayRequest.get(0)).getTime();
        long endTimeRequest = sdf.parse(timeInDayRequest.get(1)).getTime();
        return (startTimeRequest >= startTime || endTimeRequest <= startTime) &&
                (startTimeRequest < startTime || endTimeRequest > endTime) &&
                (startTimeRequest >= endTime || endTimeRequest <= endTime);
    }


    public StaffResponse updatePasswordAllStaff() {
        UserAdmin userAdmin = getCurrentUser().getUserAdmin();
        if (userAdmin == null) {
            return new StaffResponse("This account is not authorized", 4001);
        }
        List<StaffEntity> staffEntities = repository.findAllByIsDeletedFalse();
        staffEntities.forEach(staffEntity -> {
            if (staffEntity.getPhoneNumber() != null && !staffEntity.getPhoneNumber().equals("")) {
                staffEntity.setPassword(passwordEncoder.encode(setPasswordDefault(staffEntity.getPhoneNumber())));
                staffEntity.setPasswordEncode(userAdminService.encodePasswordCleaner(
                        setPasswordDefault(staffEntity.getPhoneNumber())));
            }
        });
        repository.saveAll(staffEntities);
        return new StaffResponse();
    }

    /*public StaffScheduleResponse createScheduleByReceptionist(StaffScheduleRequest scheduleRequest) {
        StaffEntity staffEntity = repository.findStaffById(scheduleRequest.getStaffId());
        if (staffEntity == null) {
            return new StaffScheduleResponse("This account not authorize", 4001);
        }
        List<StaffInStore> staffInStores = new ArrayList<>();
        List<DaysOfWeekRequest> daysOfWeekRequests = scheduleRequest.getDaysOfWeekRequests();
        Set<Long> storeIds = daysOfWeekRequests.stream().map(DaysOfWeekRequest::getStoreId).collect(Collectors.toSet());
        if (storeIds.size() >= MAX_STORE_NUMBERS) {
            return new StaffScheduleResponse("Staff can only work at most 3 stores", 4002);
        }

        Map<Long, List<DaysOfWeekRequest>> daysOfWeekRequestMap = daysOfWeekRequests.stream()
                .collect(Collectors.groupingBy(DaysOfWeekRequest::getStoreId));
        Map<String, List<DaysOfWeekRequest>> daysOfWeekMap = daysOfWeekRequests.stream()
                .collect(Collectors.groupingBy(DaysOfWeekRequest::getDaysOfWeek));
        if (!checkScheduleStaff(daysOfWeekMap)) {
            return new StaffScheduleResponse("Duplicate shift work", 4005);
        }
        for (Long storeId : storeIds) {
            StaffInStore staffInStore = new StaffInStore();
            staffInStore.setStoreId(storeId);
            staffInStore.setStaffId(staffEntity.getId());
            List<DaysOfWeekRequest> daysOfWeeks = daysOfWeekRequestMap.get(storeId);
            for (DaysOfWeekRequest dayOfWeek : daysOfWeeks) {
                String day = dayOfWeek.getDaysOfWeek();
                String timeWork = dayOfWeek.getStartTime() + ';' + dayOfWeek.getEndTime();
                saveTimeSchedule(day, timeWork, staffInStore);
            }
            staffInStores.add(staffInStore);
        }
        staffInStoreService.saveStaffInStoreList(staffInStores);
        return new StaffScheduleResponse();
    }*/

    @IsStaffUser



    public String getPasswordStaff(String phone) {
        StaffEntity staffEntity = repository.findByPhoneNumber(phone);
        if (staffEntity == null) {
            return "not found";
        }
        return userAdminService.decodePasswordOwnerStore(staffEntity.getPasswordEncode());
    }


    // Reviewed and refactor to getStaffByComboPacksNew @vylv
    /*public StaffUserResponse getStaffByComboPacks(StaffComboRequest staffComboRequest) {
        UserEntity userEntity = getCurrentUser().getUserEntity();
        Long userId = staffComboRequest.getUserId();
        if (userEntity != null) {
            userId = userEntity.getId();
        }
        Long storeId = staffComboRequest.getStoreId();

        List<StaffUserDTO> staffUserDTOS = repository.findStaffByComboPacks(staffComboRequest.getStoreId(),
                staffComboRequest.getDesiredTime());
        Map<Long, StaffUserDTO> staffUserDTOMap = new HashMap<>();
        Map<Long, Set<Long>> mapStaffIdAndComboPackIds = new HashMap<>();
        for (StaffUserDTO staffUserDTO : staffUserDTOS) {
            Set<Long> comboPackIdsOfStaff = mapStaffIdAndComboPackIds
                    .getOrDefault(staffUserDTO.getId(), new HashSet<>());
            comboPackIdsOfStaff.add(staffUserDTO.getComboPackId());
            mapStaffIdAndComboPackIds.put(staffUserDTO.getId(), comboPackIdsOfStaff);
            staffUserDTOMap.put(staffUserDTO.getId(), staffUserDTO);
        }
        Set<Long> staffIds = staffUserDTOS.stream().map(StaffUserDTO::getId).collect(toSet());
        Set<Long> staffIdsChosen = staffComboRequest.getStaffIdsChosen();
        if (staffIdsChosen != null) {
            staffIds.removeAll(staffIdsChosen);
        }

        Set<Long> staffIdsNotGood = notGoodService.findByStoreIdAndStaffId(storeId, userId);

        Map<Integer, Set<Long>> userIdAndStaffIdsMap = new HashMap<>();
        List<UserComboRequest> userComboRequests = staffComboRequest.getUserComboRequests();

        // duplicate because find staffUserDTO by combo packs
        List<StaffUserDTO> staffUserDTOResult = new ArrayList<>();

        for (UserComboRequest userComboRequest : userComboRequests) {
            Set<Long> staffIdsResult = new HashSet<>();
            //only owner user have not good
            if (userComboRequest.getOrdinalNumber() == ORDINAL_USER_OWNER) {
                staffIds.removeAll(staffIdsNotGood);
            }
            for (Long staffId : staffIds) {
                if (userComboRequest.getComboPackIds().isEmpty()) {
                    staffIdsResult.add(staffId);
                } else {
                    Set<Long> comboPacksOfStaff = mapStaffIdAndComboPackIds.get(staffId);
                    if (comboPacksOfStaff.containsAll(userComboRequest.getComboPackIds())) {
                        staffIdsResult.add(staffId);
                    }
                }
            }
            userIdAndStaffIdsMap.put(userComboRequest.getOrdinalNumber(), staffIdsResult);
        }

        for (Long staffId : staffIds) {
            StaffUserDTO staffUserDTO = staffUserDTOMap.get(staffId);
            staffUserDTOResult.add(staffUserDTO);
        }

        staffUserDTOResult.addAll(staffUserDTOMap.values().stream()
                .filter(staffUserDTO -> staffIds.contains(staffUserDTO.getId()))
                .collect(toList()));

        List<StaffSexDTO> staffSexDTOS = groupStaffDTOSBySex(staffUserDTOResult);

        StaffUserResponse staffUserResponse = new StaffUserResponse();
        staffUserResponse.setUserAndStaffIds(userIdAndStaffIdsMap);
        staffUserResponse.setStaffSexDTOS(staffSexDTOS);
        return staffUserResponse;
    }*/



    private List<StaffSexDTO> groupStaffDTOSBySex(List<StaffUserDTO> staffUserDTOResult) {
        Map<String, List<StaffUserDTO>> staffBySexMap = staffUserDTOResult.stream()
                .collect(Collectors.groupingBy(StaffUserDTO::getSex));
        List<StaffSexDTO> staffSexDTOS = new ArrayList<>();
        for (String sex : SEXES) {
            StaffSexDTO staffSexDTO = new StaffSexDTO();
            staffSexDTO.setSex(sex);
            List<StaffUserDTO> staffUserDTOSBySex = staffBySexMap.get(sex);
            if (staffUserDTOSBySex != null) {
                staffSexDTO.setStaffUserDTOS(staffUserDTOSBySex);
            }
            staffSexDTOS.add(staffSexDTO);
        }
        return staffSexDTOS;
    }




    public StaffEntity findStaffByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }

    private StaffResponse getStaffDTOPaging(Pageable pageable, List<StaffDTO> staffDTOS) {
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), staffDTOS.size());
        if (start > end) {
            return new StaffResponse("Paging error", 4007);
        }
        final Page<StaffDTO> page = new PageImpl<>(staffDTOS.subList(start, end), pageable, staffDTOS.size());
        List<StaffDTO> staffDTOSublist = staffDTOS.subList(start, end);

        return new StaffResponse(staffDTOSublist, populatePageDto(page));
    }

}
