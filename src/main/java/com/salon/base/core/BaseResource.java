package com.salon.base.core;

import com.salon.base.security.CurrentUserDetailsContainer;
import com.salon.custom.entities.UserEntity;
import com.salon.custom.exception.BaseException;
import com.salon.custom.security.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseResource<S extends BaseService> {

    @Autowired
    protected S service;

    @Autowired
    private CurrentUserDetailsContainer userDetailsContainer;

    public CustomUserDetail getCurrentUser(){
        return this.userDetailsContainer.getUserDetails();
    }

    public Long getUserEntityId() {
        CustomUserDetail customUserDetail = this.userDetailsContainer.getUserDetails();
        UserEntity user = customUserDetail.getUserEntity();
        if(user == null) {
            throw new BaseException("User not found", 404);
        }

        return user.getId();
    }

    public UserEntity getUserEntity() {
        CustomUserDetail customUserDetail = this.userDetailsContainer.getUserDetails();
        UserEntity user = customUserDetail.getUserEntity();
        if(user == null) {
            throw new BaseException("User not found", 404);
        }

        return user;
    }

//
//    @PutMapping("/update")
//    public Object update(@AuthenticationPrincipal CustomUserDetails currentUser,
//                                          @RequestBody D domain) {
//        ValidateResponse validateResponse = service.validateAndAuth(currentUser, domain, ApiMode.UPDATE);
//        if(validateResponse.isValid()) {
//            return ResponseEntity.ok(service.update(domain, currentUser));
//        } else {
//            return ResponseEntity.status(validateResponse.getHttpStatus()).body(validateResponse);
//        }
//    }
//
//    @DeleteMapping("/delete")
//    public Object delete(@AuthenticationPrincipal CustomUserDetails currentUser,
//                                          @RequestBody UUID code) {
//        ValidateResponse validateResponse = service.validateAndAuth(currentUser, code, ApiMode.DELETE);
//        if(validateResponse.isValid()) {
//            return ResponseEntity.ok().body(service.delete(currentUser, code));
//        } else {
//            return ResponseEntity.status(validateResponse.getHttpStatus()).body(validateResponse);
//        }
//    }
//


}
