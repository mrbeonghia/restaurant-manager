/*
package com.salon.custom.rest;

import com.salon.base.core.BaseResource;
import com.salon.custom.dto.booking.staff_history.StaffHistoryResponse;
import com.salon.custom.dto.staff.StaffRequest;
import com.salon.custom.dto.staff.StaffResponse;
import com.salon.custom.dto.staff.StaffSignInDTO;
import com.salon.custom.dto.staff.monthly_value.MonthlyValueResponse;
import com.salon.custom.dto.staff.staff_combo.ComboPackIdsRequest;
import com.salon.custom.dto.staff.staff_combo.GetStaffByComboPackResponse;
import com.salon.custom.dto.staff.staff_combo.ListComboPackIdsRequest;
import com.salon.custom.dto.staff.staff_combo.StaffComboRequest;
import com.salon.custom.dto.staff.staff_user.StaffUserRequest;
import com.salon.custom.dto.staff.staff_user.StaffUserResponse;
import com.salon.custom.service.StaffInStoreService;
import com.salon.custom.service.StaffService;
import com.salon.custom.service.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class StaffResource extends BaseResource<StaffService> {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("api/v1/admin/staff")
    public ResponseEntity<StaffResponse> createStaffBySystemAdmin(@RequestBody StaffRequest request) {
        StaffResponse staffResponse = service.createStaffBySystemAdmin(request);
        return ResponseEntity.ok().body(staffResponse);
    }

    @PutMapping("api/v1/admin/staff")
    public ResponseEntity<StaffResponse> updateStaffByAdmin(@RequestBody StaffRequest request) {
        StaffResponse staffResponse = service.updateStaffByAdmin(request);
        return ResponseEntity.ok().body(staffResponse);
    }

    @GetMapping("api/v1/app/staff")
    public ResponseEntity<StaffResponse> getStaffForUserApp(@RequestParam(name = "storeId", required = false) Long storeId,
                                                            @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
                                                            @RequestParam(name = "date") Date date,
                                                            @RequestParam(name = "time") String time,
                                                            @RequestParam(name = "packageCareId") Long packageCareId,
                                                            @RequestParam(name = "orderId", required = false) Long orderId) {
        StaffResponse staffResponse = service.getStaffForUserApp(storeId, date, time, packageCareId, orderId);
        return ResponseEntity.ok().body(staffResponse);
    }

    @GetMapping("api/v1/admin/staff")
    public ResponseEntity<StaffResponse> getStaffForAdmin(@RequestParam(name = "storeId", required = false) Long storeId,
                                                          @RequestParam(name = "staffId", required = false) Long staffId,
                                                          @RequestParam(name = "staffName", required = false) String staffName,
                                                          @RequestParam(name = "page", defaultValue = "1") int page,
                                                          @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        StaffResponse staffResponse = service.getStaffForAdmin(storeId, staffId, staffName, pageable);
        return ResponseEntity.ok().body(staffResponse);
    }

    @GetMapping("v1/app/staff")
    public ResponseEntity<StaffResponse> getStaffForUserApp(@RequestParam(name = "storeId") Long storeId,
                                                            @RequestParam(name = "page", defaultValue = "1") int page,
                                                            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        StaffResponse staffResponse = service.getStaffForUserApp(storeId, pageable);
        return ResponseEntity.ok().body(staffResponse);
    }

    @GetMapping("api/v1/admin/searchStaff")
    public ResponseEntity<StaffResponse> searchStaff(@RequestParam(name = "name", required = false) String name,
                                                     @RequestParam(name = "storeId", required = false) Long storeId,
                                                     @RequestParam(name = "page", defaultValue = "1") int page,
                                                     @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        StaffResponse staffResponse = service.searchStaff(name, storeId, pageable);
        return ResponseEntity.ok().body(staffResponse);
    }

    @DeleteMapping("api/v1/admin/staff")
    public ResponseEntity<StaffResponse> deleteStaffForAdmin(@RequestParam(name = "staffId") Long staffId) {
        StaffResponse staffResponse = service.deleteStaffForAdmin(staffId);
        return ResponseEntity.ok().body(staffResponse);
    }

    @GetMapping("api/v2/app/getStaffByStore")
    public ResponseEntity<StaffResponse> getStaffByStoreQr(@RequestParam(name = "qrCode") String qrCode) {
        StaffResponse staffResponse = service.getStaffByStoreQr(qrCode);
        return ResponseEntity.ok().body(staffResponse);
    }

    @GetMapping("api/v2/app/getStaffByStoreId")
    public ResponseEntity<StaffResponse> getStaffByStoreId(@RequestParam(name = "storeId") Long storeId) {
        StaffResponse staffResponse = service.getStaffByStoreId(storeId);
        return ResponseEntity.ok().body(staffResponse);
    }

    */
/*@PutMapping("api/v2/admin/updateOldStaffInStores")
    public ResponseEntity<StaffResponse> updateOldStaffInStores() {
        StaffResponse staffResponse = service.updateOldStaffInStore();
        return ResponseEntity.ok().body(staffResponse);
    }*//*


    @PostMapping("api/v2/admin/createStaffByStore")
    public ResponseEntity<StaffResponse> createStaffByStore(@RequestBody StaffRequest request) {
        StaffResponse staffResponse = service.createStaffByStoreAdmin(request);
        return ResponseEntity.ok().body(staffResponse);
    }

    @GetMapping("api/v2/admin/getStaffByPhoneNumber")
    public ResponseEntity<StaffResponse> getStaffByPhoneNumber(@RequestParam(name = "phoneNumber") String phoneNumber) {
        StaffResponse staffResponse = service.getStaffByPhoneNumber(phoneNumber);
        return ResponseEntity.ok().body(staffResponse);
    }

    @GetMapping("api/v2/admin/getStaffById")
    public ResponseEntity<StaffResponse> getStaffById(@RequestParam(name = "staffId") Long staffId) {
        StaffResponse staffResponse = service.getStaffById(staffId);
        return ResponseEntity.ok().body(staffResponse);
    }

    @PostMapping("v2/staff/sign-in")
    public ResponseEntity<StaffResponse> userStaffLogin(@RequestBody StaffSignInDTO staffSignInDTO) {

        StaffResponse staffResponse = service.userStaffLogIn(staffSignInDTO);
        return ResponseEntity.ok().body(staffResponse);
    }

    @PostMapping("v2/staff/logout")
    public ResponseEntity<StaffResponse> userStaffLogout(@RequestParam("refreshToken") String refreshToken,
                                                         @RequestParam(name = "deviceId", required = false) String deviceId) {
        return ResponseEntity.ok().body(authenticationService.staffLogout(refreshToken, deviceId));
    }

    @GetMapping("api/v2/staff/getProfile")
    public ResponseEntity<StaffUserResponse> getProfileStaff() {
        StaffUserResponse response = service.getProfileStaffUser();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("api/v2/staff/updateProfile")
    public ResponseEntity<StaffUserResponse> updateProfileByStaff(@RequestBody StaffUserRequest request) {
        StaffUserResponse response = service.updateProfileByStaff(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("api/v2/staff/getMonthlyValue")
    public ResponseEntity<MonthlyValueResponse> getMonthlyValueByStaff(@RequestParam(name = "month") Integer month,
                                                                       @RequestParam(name = "year") Integer year,
                                                                       @RequestParam(name = "storeId") Long storeId) {
        MonthlyValueResponse response = service.getMonthlyValueByStaff(month, year, storeId);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("api/v2/admin/updatePassword")
    public ResponseEntity<StaffResponse> updatePasswordAllStaff() {
        StaffResponse response = service.updatePasswordAllStaff();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("api/v2/admin/getPassword")
    public ResponseEntity<String> getPasswordStaff(@RequestParam(name = "phoneNumber") String phone) {
        String response = service.getPasswordStaff(phone);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("api/v2/staff/getHistoryService")
    public ResponseEntity<StaffHistoryResponse> getHistoryServiceByStaff(@RequestParam(name = "page", defaultValue = "1") int page,
                                                                         @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        StaffHistoryResponse response = service.getHistoryServiceByStaff(pageable);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("api/v2/staff/getBookingPerDay")
    public ResponseEntity<StaffHistoryResponse> getBookingPerDayInStoreByStaff(@RequestParam(name = "storeId") Long storeId,
                                                                               @RequestParam(name = "day") String day) {
        StaffHistoryResponse response = service.getBookingPerDayByStore(storeId, day);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("api/v2/getStaffByComboPacks")
    public ResponseEntity<GetStaffByComboPackResponse> getStaffByComboPacks(@RequestBody ListComboPackIdsRequest request) {
        return ResponseEntity.ok().body(service.getStaffByComboPacksNew(request));
    }

    @GetMapping("api/v2/admin/getStaffNotWorking")
    public ResponseEntity<StaffResponse> getStaffNotWorkingInDay(@RequestParam(name = "page", defaultValue = "1") int page,
                                                                 @RequestParam(name = "size", defaultValue = "10") int size,
                                                                 @RequestParam(name = "day") String day) {
        Pageable pageable = PageRequest.of(page - 1, size);
        StaffResponse response = service.getStaffNotWorkingInDay(day, pageable);
        return ResponseEntity.ok().body(response);
    }

}
*/
