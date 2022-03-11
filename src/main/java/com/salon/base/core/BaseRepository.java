package com.salon.base.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * @author kienta
 */
@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity> extends PagingAndSortingRepository<E, Long>, JpaRepository<E, Long> {

    Optional<E> findById(Long id);

    Page<E> findByDeleted(Pageable page, boolean isDeleted);
}
