package com.salon.custom.util;

import com.salon.custom.security.CustomUserDetail;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.*;
import java.util.Base64;

public class SecurityUtils {
    private SecurityUtils() {
    }

    /**
     * Get the login of the current user.
     *
     * @return the login of the current user.
     */
    public static CustomUserDetail getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();

        Object principal = securityContext.getAuthentication().getPrincipal();

        if (principal instanceof CustomUserDetail) {
            return (CustomUserDetail) principal;
        } else {
            return null;
        }
    }

    public static String serialize(CustomUserDetail userDetail) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(userDetail);
            so.flush();
            final byte[] byteArray = bo.toByteArray();
            return Base64.getEncoder().encodeToString(byteArray);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static CustomUserDetail deserialize(String data) throws InvalidClassException {
        try {
            final byte[] bytes = Base64.getDecoder().decode(data);
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream si = new ObjectInputStream(bi);
            CustomUserDetail userDetail = (CustomUserDetail) si.readObject();
            si.close();
            return userDetail;
        } catch (InvalidClassException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
