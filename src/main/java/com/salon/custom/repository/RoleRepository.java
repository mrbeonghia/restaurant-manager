package com.salon.custom.repository;

import com.salon.base.core.BaseRepository;
import com.salon.custom.entities.RoleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends BaseRepository<RoleEntity> {

    List<RoleEntity> findByDeletedFalse();

}
