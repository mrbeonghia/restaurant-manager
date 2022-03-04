package com.salon.custom.rest;

import com.salon.custom.enums.UserType;
import com.salon.custom.exception.InvalidRefreshTokenException;
import com.salon.custom.service.authentication.AuthenticationService;
import com.salon.custom.service.authentication.TokenVerifyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenResource {

    @Autowired
    private AuthenticationService authenticationService;

//    @PostMapping("/refreshAccessToken")
//    public ResponseEntity refreshAccessToken(@RequestParam("refresh_token") String refreshToken) {
//        try {
//            return ResponseEntity.ok(authenticationService.createAccessToken(refreshToken));
//        } catch (InvalidRefreshTokenException e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }

    @PostMapping("/logoutByUserId")
    public void logoutByUserId(@RequestParam("userId") Integer userId) {
        authenticationService.logoutByUserId(userId, UserType.APP_USER);
    }

    @GetMapping("/verifyAccessToken")
    public TokenVerifyResult verifyAccessToken(@RequestParam("accessToken") String accessToken) {
        return authenticationService.verifyAccessToken(accessToken);
    }
}
