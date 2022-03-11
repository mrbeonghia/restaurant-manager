package com.salon.custom.rest;

import com.salon.base.core.BaseResource;
import com.salon.custom.dto.user.UserRequest;
import com.salon.custom.dto.user.UserResponse;
import com.salon.custom.service.UserService;
import com.salon.custom.service.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserResource extends BaseResource<UserService> {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("api/createUser")
    public ResponseEntity<UserResponse> createUserByAdmin(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = service.createUser(userRequest);
        return ResponseEntity.ok().body(userResponse);
    }

    @PutMapping("api/updateUser")
    public ResponseEntity<UserResponse> updateUserByAdmin(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = service.updateUser(userRequest);
        return ResponseEntity.ok().body(userResponse);
    }

    @GetMapping("api/getUsers")
    public ResponseEntity<UserResponse> getUsers(@RequestParam(value = "search", required = false) String search,
                                                 @RequestParam(name = "page", defaultValue = "1") int page,
                                                 @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        UserResponse userResponse = service.getListUser(search, pageable);
        return ResponseEntity.ok().body(userResponse);
    }



}
