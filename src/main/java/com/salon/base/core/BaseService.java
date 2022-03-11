package com.salon.base.core;

import com.salon.base.security.CurrentUserDetailsContainer;
import com.salon.custom.dto.base.PageDto;
import com.salon.custom.entities.AuthenticationEventEntity;
import com.salon.custom.security.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author kienta
 */
@Service
public abstract class BaseService<E extends BaseEntity, R extends BaseRepository> {

    @Autowired
    protected R repository;

    @Autowired
    private CurrentUserDetailsContainer currentUserDetailsContainer;

    public CustomUserDetail getCurrentUser(){
        return this.currentUserDetailsContainer.getUserDetails();
    }

    /**
     * Create
     */
    public E save(E entity) {
        if (entity == null) {
            return null;
        }

        preSave(entity);

        return (E) repository.save(entity);
    }

    /**
     * Populate page dto
     * @param page page
     * @return PageDto
     */
    protected PageDto populatePageDto(Page<?> page){
        if(page == null) {
            return null;
        }

        PageDto pageDto = new PageDto();

        pageDto.setLast(page.isLast());
        pageDto.setTotalElements(page.getTotalElements());
        pageDto.setSize(page.getSize());
        pageDto.setNumber(page.getNumber() + 1);
        pageDto.setSort(page.getSort());
        pageDto.setNumberOfElements(page.getNumberOfElements());
        pageDto.setFirst(page.isFirst());
        pageDto.setTotalPages(page.getTotalPages());

        return pageDto;
    }

    public void preSave(E entity) {
        entity.setCreatedTime(new Date());
        entity.setUpdatedTime(new Date());
    }

    /**
     * Service Read
     */
    public Optional<AuthenticationEventEntity> findById(Long id) {
        Object entity = repository.findById(id).orElse(null);

        if (entity == null) {
            return null;
        }

        return (Optional<AuthenticationEventEntity>) entity;
    }

    /**
     * Update
     */
    public E update(E entity) {
        if (entity == null || entity.getId() == null) {
            return null;
        } else {

            return (E) repository.save(entity);
        }
    }

    /**
     * Get findAll
     *
     * @param page
     * @return
     */
    public Page<E> findAll(Pageable page) {
        return repository.findAll(page);
    }

	/**
	 * Get findAll
	 * @param page
	 * @return
	 */
	public Page<E> findAll(Pageable page, boolean isDeleted) {
		return repository.findByDeleted(page, isDeleted);
	}
}
