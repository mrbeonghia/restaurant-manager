package com.salon.custom.security.role.annotation;

import com.salon.custom.security.CustomUserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component(value = "customPreAuthorizer")
@Slf4j
public class CustomPreAuthorizer {
    public boolean isMatchUser(Long userId) {
        try {
            log.info("checking ... userId {}", userId);
            var userDetails = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return userDetails.getUserEntity().getId().equals(userId);
        } catch (Exception exception) {
            return false;
        }
    }
}
