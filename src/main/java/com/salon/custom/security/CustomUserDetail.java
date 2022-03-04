package com.salon.custom.security;

import com.salon.custom.entities.StaffEntity;
import com.salon.custom.entities.UserAdmin;
import com.salon.custom.entities.UserEntity;
import com.salon.custom.enums.Roles;
import com.salon.custom.enums.UserType;
import com.salon.custom.util.Constant;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class CustomUserDetail implements UserDetails {

    UserEntity userEntity;

    UserAdmin userAdmin;

    StaffEntity staffEntity;

    UserType userType;


    public CustomUserDetail(UserEntity userEntity) {
        super();
        this.userType = UserType.APP_USER;
        this.userEntity = userEntity;
    }

    public CustomUserDetail(UserAdmin userAdmin) {
        super();
        this.userType = UserType.SYSTEM_USER;
        this.userAdmin = userAdmin;
    }

    public CustomUserDetail(StaffEntity staffEntity) {
        super();
        this.userType = UserType.STAFF_USER;
        this.staffEntity = staffEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userType.name()));

        return authorities;
    }

    public Long getUserId() {
        if (userEntity == null && userAdmin == null && staffEntity == null)
            return null;
        if (userEntity != null)
            return userEntity.getId();
        if (staffEntity != null)
            return staffEntity.getId();
        return userAdmin.getId();
    }

    @Override
    public String getPassword() {
//        return this.userType.equals(UserType.SYSTEM_USER) ? userAdmin.getPassword() : userEntity.getPassword();
        if (this.userType.equals(UserType.SYSTEM_USER)){
            return userAdmin.getPassword();
        }
        if (this.userType.equals(UserType.APP_USER)){
            return userEntity.getPassword();
        }
        return staffEntity.getPassword();
    }

    @Override
    public String getUsername() {
//        return this.userType.equals(UserType.SYSTEM_USER) ? userAdmin.getUserName() : userEntity.getEmail();
        if (this.userType.equals(UserType.SYSTEM_USER)){
            return userAdmin.getEmail();
        }
        if (this.userType.equals(UserType.APP_USER)){
            return userEntity.getEmail();
        }
        return staffEntity.getPhoneNumber();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserAdmin getUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(UserAdmin userAdmin) {
        this.userAdmin = userAdmin;
    }

    public StaffEntity getStaffEntity() {
        return staffEntity;
    }

    public void setStaffEntity(StaffEntity staffEntity) {
        this.staffEntity = staffEntity;
    }
}
