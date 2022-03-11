package com.salon.custom.service;

import com.salon.base.core.BaseService;
import com.salon.custom.dto.user.UserDTO;
import com.salon.custom.dto.user.UserRequest;
import com.salon.custom.dto.user.UserResponse;
import com.salon.custom.entities.UserEntity;
import com.salon.custom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends BaseService<UserEntity, UserRepository> {

    private UserDTO toDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setBirthday(userEntity.getBirthday());
        userDTO.setName(userEntity.getName());
        userDTO.setPhoneNumber(userEntity.getPhoneNumber());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setGender(userEntity.getGender());
        return userDTO;
    }

    private void toEntity(UserEntity userEntity, UserRequest userRequest) {
        userEntity.setBirthday(userRequest.getBirthday());
        userEntity.setName(userRequest.getName());
        userEntity.setPhoneNumber(userRequest.getPhoneNumber());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setGender(userRequest.getGender());
    }

    public UserResponse createUser(UserRequest userRequest){
        UserEntity userExist = repository.findByPhoneNumberAndDeletedFalse(userRequest.getPhoneNumber());
        if (userExist != null){
            return new UserResponse("This user exist", 4002);
        }
        UserEntity userEntity = new UserEntity();
        toEntity(userEntity, userRequest);
        save(userEntity);
        return new UserResponse(toDTO(userEntity));
    }

    public UserResponse updateUser(UserRequest userRequest){
        UserEntity userEntity = repository.findByIdAndDeletedFalse(userRequest.getId());
        if (userEntity == null){
            return new UserResponse("This user not found", 4004);
        }
        toEntity(userEntity, userRequest);
        update(userEntity);
        return new UserResponse(toDTO(userEntity));
    }

    public UserResponse deleteUser(Long id){
        UserEntity userEntity = repository.findByIdAndDeletedFalse(id);
        if (userEntity == null){
            return new UserResponse("This user not found", 4004);
        }
        userEntity.setDeleted(true);
        update(userEntity);
        return new UserResponse(toDTO(userEntity));
    }

    public UserResponse getListUser(String search, Pageable pageable){
        Page<UserEntity> userEntities = repository.searchUser(search, pageable);
        List<UserDTO> userDTOS = new ArrayList<>();
        userEntities.forEach(userEntity -> userDTOS.add(toDTO(userEntity)));
        return new UserResponse(userDTOS, populatePageDto(userEntities));
    }


    public void saveUser(UserEntity userEntity) {
        repository.save(userEntity);
    }



}
