package com.salon.custom.repository;

import com.salon.base.core.BaseRepository;
import com.salon.custom.entities.TableEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TableRepository extends BaseRepository<TableEntity> {

    Page<TableEntity> findByDeletedFalse(Pageable page);

    List<TableEntity> findByDeletedFalse();

    TableEntity findByIdAndDeletedFalse(Long id);

    TableEntity findByNameAndDeletedFalse(String name);

    List<TableEntity> findByIdInAndDeletedFalse(Set<Long> ids);

}
