package com.salon.custom.service;

import com.salon.base.core.BaseService;
import com.salon.custom.entities.RoleEntity;
import com.salon.custom.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService extends BaseService<RoleEntity, RoleRepository> {

    public List<RoleEntity> getAllRoles(){
        return repository.findByDeletedFalse();
    }

}
