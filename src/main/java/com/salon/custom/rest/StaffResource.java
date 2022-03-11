package com.salon.custom.rest;

import com.salon.base.core.BaseResource;
import com.salon.custom.dto.staff.StaffRequest;
import com.salon.custom.dto.staff.StaffResponse;
import com.salon.custom.dto.staff.StaffSignInDTO;
import com.salon.custom.service.StaffService;
import com.salon.custom.service.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class StaffResource extends BaseResource<StaffService> {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("createStaff")
    public ResponseEntity<StaffResponse> createStaffBySystemAdmin(@RequestBody StaffRequest request) {
        StaffResponse staffResponse = service.createStaff(request);
        return ResponseEntity.ok().body(staffResponse);
    }

    @PutMapping("api/updateStaff")
    public ResponseEntity<StaffResponse> updateStaffByAdmin(@RequestBody StaffRequest request) {
        StaffResponse staffResponse = service.createStaff(request);
        return ResponseEntity.ok().body(staffResponse);
    }

    @GetMapping("api/getStaff")
    public ResponseEntity<StaffResponse> getStaffForUserApp(@RequestParam(name = "storeId", required = false) Long storeId,
                                                            @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
                                                            @RequestParam(name = "date") Date date,
                                                            @RequestParam(name = "time") String time,
                                                            @RequestParam(name = "packageCareId") Long packageCareId,
                                                            @RequestParam(name = "orderId", required = false) Long orderId) {
        StaffResponse staffResponse = service.createStaff(null);
        return ResponseEntity.ok().body(staffResponse);
    }

    @PostMapping("staffLogIn")
    public ResponseEntity<StaffResponse> staffLogIn(@RequestBody StaffSignInDTO request) {
        StaffResponse staffResponse = service.staffLogIn(request);
        return ResponseEntity.ok().body(staffResponse);
    }


}
