package com.salon.base.security;

import com.salon.custom.security.CustomUserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *  class hold current login user detail instance.
 */
@Component
public class CurrentUserDetailsContainer {

    /**
     *  get user details of current login user.
     * @return CustomUserDetails instance if user authenticated , return null if user not authenticated.
     */
    public CustomUserDetail getUserDetails() {
        if (SecurityContextHolder.getContext() != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {

                Object principal = authentication.getPrincipal();
                if (principal instanceof CustomUserDetail) {
                    return (CustomUserDetail) principal;
                }
            }
        }
        return null;
    }

}
