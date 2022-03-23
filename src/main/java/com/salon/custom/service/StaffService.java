package com.salon.custom.service;

import com.salon.base.core.BaseService;
import com.salon.custom.dto.LoginResult;
import com.salon.custom.dto.staff.StaffDTO;
import com.salon.custom.dto.staff.StaffRequest;
import com.salon.custom.dto.staff.StaffResponse;
import com.salon.custom.dto.staff.StaffSignInDTO;
import com.salon.custom.entities.RoleEntity;
import com.salon.custom.entities.Staff;
import com.salon.custom.enums.Roles;
import com.salon.custom.exception.InvalidRefreshTokenException;
import com.salon.custom.repository.StaffRepository;
import com.salon.custom.security.CustomUserDetail;
import com.salon.custom.service.authentication.AuthenticationEventService;
import com.salon.custom.service.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.salon.custom.util.Constant.LENGTH_PWD_STAFF_DEFAULT;
import static com.salon.custom.util.Constant.SECRET_KEY;

@Service
public class StaffService extends BaseService<Staff, StaffRepository> {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationEventService authenticationEventService;

    @Autowired
    private RoleService roleService;

    public StaffResponse staffLogIn(StaffSignInDTO staffSignInDTO) {
        Staff staff = repository.findByPhoneNumberAndDeletedFalse(staffSignInDTO.getPhone());
        if (staff == null) {
            return new StaffResponse("Phone staff incorrect", 4004);
        }
        boolean checkPass = passwordEncoder.matches(staffSignInDTO.getPassword(), staff.getPassword());
        if (!checkPass) {
            return new StaffResponse("Password staff incorrect", 4101);
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        CustomUserDetail customUserDetail = new CustomUserDetail(staff);
        grantedAuthorities.add(new SimpleGrantedAuthority(Roles.STAFF.getName()));
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(customUserDetail, staff.getPassword());

        List<RoleEntity> roles = roleService.getAllRoles();
        Map<Long, String> idToRole = roles.stream().collect(Collectors.toMap(RoleEntity::getId, RoleEntity::getName));

        StaffResponse staffResponse = new StaffResponse();
        String refreshToken = authenticationService.createRefreshToken(authenticationToken);
        try {
            String accessToken = authenticationService.createAccessToken(refreshToken);
            StaffDTO staffDTO = toDTO(staff);


            LoginResult result = new LoginResult();
            result.setRefreshToken(refreshToken);
            result.setAccessToken(accessToken);
            result.setUserId(staff.getId());
            staffResponse.setStaffDTO(staffDTO);
            staffResponse.setLoginResult(result);

        } catch (InvalidRefreshTokenException e) {
            staffResponse.setCode(4001);
            staffResponse.setSuccess(false);
        }
        return staffResponse;
    }

    private StaffDTO toDTO(Staff staff) {
        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setId(staff.getId());
        staffDTO.setName(staff.getName());
        staffDTO.setBirthday(staff.getBirthday());
        staffDTO.setAvatarUrl(staff.getAvatarUrl());
        staffDTO.setPhoneNumber(staff.getPhoneNumber());
        staffDTO.setEmail(staff.getEmail());
        staffDTO.setGender(staff.getGender());
        staffDTO.setRole(staff.getRole().getName());
        staffDTO.setType(staff.getRole().getName());

        return staffDTO;
    }

    private void toEntity(StaffRequest staffRequest, Staff staff) {
        staff.setName(staffRequest.getName());
        staff.setBirthday(staffRequest.getBirthday());
        staff.setAvatarUrl(staffRequest.getAvatarUrl());
        staff.setPhoneNumber(staffRequest.getPhoneNumber());
        staff.setEmail(staffRequest.getEmail());
        staff.setGender(staffRequest.getGender());
        staff.setRole(roleService.getRoleByName(staffRequest.getRole()));
    }

    public StaffResponse createStaff(StaffRequest staffRequest) {
        Staff staffExist = repository.findByPhoneNumberAndDeletedFalse(staffRequest.getPhoneNumber());
        if (staffExist != null) {
            return new StaffResponse("This staff exist", 4002);
        }
        Staff staff = new Staff();
        toEntity(staffRequest, staff);
        staff.setPassword(passwordEncoder.encode(setPasswordDefault(staff.getPhoneNumber())));
        staff.setPasswordEncode(encodePasswordCleaner(setPasswordDefault(staff.getPhoneNumber())));
        save(staff);
        return new StaffResponse(toDTO(staff));

    }

    public StaffResponse updateStaff(StaffRequest staffRequest) {
        Staff staff = repository.findByIdAndDeletedFalse(staffRequest.getId());
        if (staff == null) {
            return new StaffResponse("This staff not found", 4004);
        }
        toEntity(staffRequest, staff);
        staff.setPassword(passwordEncoder.encode(setPasswordDefault(staff.getPhoneNumber())));
        staff.setPasswordEncode(encodePasswordCleaner(setPasswordDefault(staff.getPhoneNumber())));
        update(staff);
        return new StaffResponse(toDTO(staff));
    }

    public StaffResponse deleteStaff(Long id) {
        Staff staff = repository.findByIdAndDeletedFalse(id);
        if (staff == null) {
            return new StaffResponse("This staff not found", 4004);
        }
        staff.setDeleted(true);
        update(staff);
        return new StaffResponse(toDTO(staff));
    }


    private String setPasswordDefault(String phoneNumber) {
        String passwordDefault = "";
        if (phoneNumber.length() > LENGTH_PWD_STAFF_DEFAULT) {
            passwordDefault = phoneNumber.substring(phoneNumber.length() - LENGTH_PWD_STAFF_DEFAULT);
        }
        return passwordDefault;
    }

    String encodePasswordCleaner(String password) {
        String passwordEncode = null;
        SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] byteEncrypted = cipher.doFinal(password.getBytes());
            passwordEncode = Base64.getEncoder().encodeToString(byteEncrypted);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException
                | IllegalBlockSizeException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return passwordEncode;
    }

    public StaffResponse getListStaff(String search, Pageable pageable) {
        Page<Staff> staff = repository.findByDeletedFalseOrderByIdDesc(pageable);
        List<StaffDTO> staffDTOS = new ArrayList<>();
        staff.forEach(userEntity -> staffDTOS.add(toDTO(userEntity)));
        return new StaffResponse(staffDTOS, populatePageDto(staff));
    }


}
