package com.salon.custom.rest;

import com.salon.custom.dto.user.*;
import com.salon.custom.dto.user.user_update_info.UserUpdateInfoResponse;
import com.salon.custom.dto.user.user_visit.UserVisitResponse;
import com.salon.custom.exception.BaseException;
import com.salon.custom.service.UserService;
import com.salon.custom.service.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("v1/app/registerSendOtpEmail")
    public ResponseEntity<UserResponse> registerSendOtpEmail(@RequestParam("email") String email) {

        try {
            UserResponse userResponse = userService.registerSendOtpEmail(email);
            return ResponseEntity.ok().body(userResponse);
        } catch (BaseException e) {
            return ResponseEntity.ok().body(new UserResponse(e.getMessage(), e.getCode()));
        }

    }



    @PostMapping("api/v1/app/updateProfile")
    public ResponseEntity<UserResponse> updateProfileUserApp(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.updateProfileUserApp(userRequest);
        return ResponseEntity.ok().body(userResponse);
    }



    @PostMapping("v1/app/logout")
    public ResponseEntity<UserResponse> logout(@RequestParam("refreshToken") String refreshToken,
                                               @RequestParam(name = "deviceId", required = false) String deviceId) {
        return ResponseEntity.ok().body(authenticationService.logout(refreshToken, deviceId));
    }

    @PostMapping("api/v1/app/changePassword")
    public ResponseEntity<UserResponse> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        UserResponse userResponse = userService.changePassword(changePasswordRequest);
        return ResponseEntity.ok().body(userResponse);
    }


    @GetMapping(value = "v1/app/forgot-password")
    public ResponseEntity<UserResponse> forgotPassword(@RequestParam(name = "email") String email) {
        UserResponse userResponse = userService.resetPassword(email);
        return ResponseEntity.ok().body(userResponse);
    }

    @PostMapping("v1/app/refreshAccessToken")
    public ResponseEntity<UserResponse> refreshAccessToken(@RequestParam("refresh_token") String refreshToken) {
        return ResponseEntity.ok(authenticationService.createAccessTokenWhenRefresh(refreshToken));
    }

    @GetMapping("api/v1/app/getInfoUser")
    public ResponseEntity<UserResponse> getInfoUser() {
        UserResponse userResponse = userService.getInfoUser();
        return ResponseEntity.ok().body(userResponse);
    }

    @PostMapping("v2/app/registerVerifyEmail")
    public ResponseEntity<UserResponse> registerVerificationEmail(@RequestParam("email") String email) {
        try {
            UserResponse userResponse = userService.registerVerificationEmail(email);
            return ResponseEntity.ok().body(userResponse);
        } catch (BaseException e) {
            return ResponseEntity.ok().body(new UserResponse(e.getMessage(), e.getCode()));
        }
    }



//    @PatchMapping("api/v2/admin/updateCustomIdAllUsers")
//    public ResponseEntity<UserResponse> updateCustomerId() {
//        UserResponse userResponse = userService.updateCustomerId();
//        return ResponseEntity.ok().body(userResponse);
//    }



    @PutMapping("api/v2/app/setStoreFavourite")
    public ResponseEntity<UserResponse> setStoreFavourite(@RequestParam(name = "storeId") Long storeId) {
        UserResponse userResponse = userService.setStoreFavourite(storeId);
        return ResponseEntity.ok().body(userResponse);
    }




//    @PutMapping("api/v2/admin/updateRandomIdUser")
//    public ResponseEntity<UserResponse> updateRandomIdUser() {
//        UserResponse userResponse = userService.updateCustomerId();
//        return ResponseEntity.ok().body(userResponse);
//    }

    @PutMapping("api/v2/admin/updateUserByAdmin")
    public ResponseEntity<UserResponse> updateUserAppByAdmin(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.updateUserByAdmin(userRequest);
        return ResponseEntity.ok().body(userResponse);
    }

    @GetMapping("api/v2/app/checkInfoUser")
    public ResponseEntity<UserUpdateInfoResponse> checkInfoUser() {
        UserUpdateInfoResponse userResponse = userService.checkInfoUser();
        return ResponseEntity.ok().body(userResponse);
    }

    @PutMapping("api/v2/app/updateInfoUser")
    public ResponseEntity<UserUpdateInfoResponse> updateFuriganaAndPhone(@RequestParam(value = "furigana", required = false) String furiganaName,
                                                                         @RequestParam(value = "phone", required = false) String phoneNumber) {
        UserUpdateInfoResponse userResponse = userService.updateInfoUser(furiganaName, phoneNumber);
        return ResponseEntity.ok().body(userResponse);
    }


}
