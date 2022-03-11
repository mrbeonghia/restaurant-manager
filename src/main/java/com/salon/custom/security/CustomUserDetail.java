package com.salon.custom.security;

import com.salon.custom.entities.Staff;
import com.salon.custom.entities.UserAdmin;
import com.salon.custom.entities.UserEntity;
import com.salon.custom.enums.UserType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetail implements UserDetails {

    UserEntity userEntity;

    UserAdmin userAdmin;

    Staff staff;

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

    public CustomUserDetail(Staff staff) {
        super();
        this.userType = UserType.STAFF_USER;
        this.staff = staff;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userType.name()));

        return authorities;
    }

    public Long getUserId() {
        if (userEntity == null && userAdmin == null && staff == null)
            return null;
        if (userEntity != null)
            return userEntity.getId();
        if (staff != null)
            return staff.getId();
        return userAdmin.getId();
    }

    @Override
    public String getPassword() {
//        return this.userType.equals(UserType.SYSTEM_USER) ? userAdmin.getPassword() : userEntity.getPassword();
        if (this.userType.equals(UserType.SYSTEM_USER)){
            return userAdmin.getPassword();
        }
        /*if (this.userType.equals(UserType.APP_USER)){
            return userEntity.getPassword();
        }*/
        return staff.getPassword();
    }

    @Override
    public String getUsername() {
//        return this.userType.equals(UserType.SYSTEM_USER) ? userAdmin.getUserName() : userEntity.getEmail();
        if (this.userType.equals(UserType.SYSTEM_USER)){
            return userAdmin.getEmail();
        }
        /*if (this.userType.equals(UserType.APP_USER)){
            return userEntity.getEmail();
        }*/
        return staff.getPhoneNumber();
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

    public Staff getStaffEntity() {
        return staff;
    }

    public void setStaffEntity(Staff staff) {
        this.staff = staff;
    }
}
