package com.salon.custom.security.role.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyRole('SYSTEM_ADMIN', 'SYSTEM_USER', 'STORE_ADMIN')")
public @interface IsSystemOrStoreUser {
}
