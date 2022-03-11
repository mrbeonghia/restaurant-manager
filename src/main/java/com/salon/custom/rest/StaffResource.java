package com.salon.custom.rest;

import com.salon.base.core.BaseResource;
import com.salon.custom.dto.staff.StaffRequest;
import com.salon.custom.dto.staff.StaffResponse;
import com.salon.custom.dto.staff.StaffSignInDTO;
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
    public ResponseEntity<StaffResponse> getStaffForUserApp(@RequestParam(value = "search", required = false) String search,
                                                            @RequestParam(name = "page", defaultValue = "1") int page,
                                                            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        StaffResponse staffResponse = service.getListStaff(search, pageable);
        return ResponseEntity.ok().body(staffResponse);
    }

    @PostMapping("staffLogIn")
    public ResponseEntity<StaffResponse> staffLogIn(@RequestBody StaffSignInDTO request) {
        StaffResponse staffResponse = service.staffLogIn(request);
        return ResponseEntity.ok().body(staffResponse);
    }


}
