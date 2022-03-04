package com.salon.custom.rest;

import com.salon.custom.dto.staff.staff_admin.ReceptionistRequest;
import com.salon.custom.dto.staff.staff_admin.ReceptionistResponse;
import com.salon.custom.dto.user.*;
import com.salon.custom.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserAdminResource {

    @Autowired
    private UserAdminService userAdminService;

    @PostMapping("v1/admin/sign-in")
    public ResponseEntity<UserResponse> userAdminLogin(@RequestBody UserSignInDto userSignInDto) {

        UserResponse userResponse = userAdminService.userAdminSignIn(userSignInDto);
        return ResponseEntity.ok().body(userResponse);
    }

    @PostMapping("v1/admin/register")
    public ResponseEntity<UserResponse> userAdminRegister(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userAdminService.registerUserAdmin(userRequest);
        return ResponseEntity.ok().body(userResponse);
    }

    @GetMapping("api/v1/admin/getUserApp")
    public ResponseEntity<UserResponse> getAllUserAppForAdmin(@RequestParam(name = "page", defaultValue = "1") int page,
                                                              @RequestParam(name = "size", defaultValue = "10") int size,
                                                              @RequestParam(name = "search", required = false) String search,
                                                              @RequestParam(name = "storeId", required = false) Long storeId,
                                                              @RequestParam(name = "sex", required = false) String sex,
                                                              @RequestParam(name = "sort", required = false) String sort) {
        Pageable pageable = PageRequest.of(page - 1, size);
        UserResponse userResponse = userAdminService.getUserAppForAdmin(search, storeId, pageable, sex, sort);
        return ResponseEntity.ok().body(userResponse);
    }

    @GetMapping("api/v1/admin/findUserApp")
    public ResponseEntity<UserResponse> getUserAppForAdmin(@RequestParam(name = "page", defaultValue = "1") int page,
                                                           @RequestParam(name = "size", defaultValue = "10") int size,
                                                           @RequestParam(name = "search") String search) {
        Pageable pageable = PageRequest.of(page - 1, size);
        UserResponse userResponse = userAdminService.findUserAppForAdmin(search, pageable);
        return ResponseEntity.ok().body(userResponse);
    }

    @GetMapping("api/v1/admin/deActiveUserApp")
    public ResponseEntity<UserResponse> deActiveUserApp(@RequestParam(name = "id") Long id,
                                                        @RequestParam(name = "email") String email) {
        UserResponse userResponse = userAdminService.deActiveUserApp(id, email);
        return ResponseEntity.ok().body(userResponse);
    }

    @PutMapping("api/v1/admin/updateProfileUser")
    public ResponseEntity<UserResponse> updateProfile(@RequestBody UserRequest request) {
        UserResponse response = userAdminService.updateProfile(request);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("api/v1/admin/changePassword")
    public ResponseEntity<UserResponse> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        UserResponse response = userAdminService.changePassword(changePasswordRequest);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "v2/forgotPasswordAdmin")
    public ResponseEntity<UserResponse> resetPasswordEmail(@RequestParam(name = "email") String email) {
        UserResponse userResponse = userAdminService.resetPasswordEmail(email);
        return ResponseEntity.ok().body(userResponse);
    }


    @PutMapping("api/v2/admin/updateStoreId")
    public ResponseEntity<UserResponse> updateStoreId() {
        UserResponse userResponse = userAdminService.updateStoreIdForStoreAdmin();
        return ResponseEntity.ok().body(userResponse);
    }

    @PostMapping("api/v2/admin/createReceptionist")
    public ResponseEntity<ReceptionistResponse> createReceptionist(@RequestBody ReceptionistRequest staffAdminRequest) {
        ReceptionistResponse response = userAdminService.createReceptionistInStore(staffAdminRequest);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("api/v2/admin/getAllReceptionist")
    public ResponseEntity<ReceptionistResponse> getAllReceptionist(@RequestParam(name = "page", defaultValue = "1") int page,
                                                                   @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        ReceptionistResponse response = userAdminService.getAllStaffAdminInStore(pageable);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("api/v2/admin/updateReceptionist")
    public ResponseEntity<ReceptionistResponse> updateReceptionist(@RequestBody ReceptionistRequest staffAdminRequest) {
        ReceptionistResponse response = userAdminService.updateReceptionistInStore(staffAdminRequest);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("api/v2/admin/deleteReceptionist")
    public ResponseEntity<ReceptionistResponse> deleteReceptionist(@RequestParam("id") Long id) {
        ReceptionistResponse response = userAdminService.deleteStaffAdminInStore(id);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("api/v2/admin/updateStaffAdminOld")
    public ResponseEntity<ReceptionistResponse> updateReceptionistFromStaffAdminOld() {
        ReceptionistResponse response = userAdminService.updateReceptionistFromStaffAdmin();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("api/v2/admin/downloadUsers")
    public ResponseEntity<Resource> exportToExcel(@RequestParam(name = "search", required = false) String search,
                                                  @RequestParam(name = "storeId", required = false) Long storeId,
                                                  @RequestParam(name = "sex", required = false) String sex,
                                                  @RequestParam(name = "sort", required = false) String sort) {
        String filename = "users.xlsx";
        InputStreamResource file = new InputStreamResource(userAdminService.generateExcel(search, storeId, sex, sort));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }



}
