package com.salon.custom.service.authentication;

import com.salon.base.core.BaseService;
import com.salon.custom.entities.AuthenticationEventEntity;
import com.salon.custom.enums.UserType;
import com.salon.custom.security.CustomUserDetail;
import com.salon.custom.repository.AuthenticationEventRepository;
import com.salon.custom.util.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationEventService extends BaseService<AuthenticationEventEntity, AuthenticationEventRepository> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationEventService.class);

    @Transactional
    public AuthenticationEventEntity create(CustomUserDetail userDetail, long timeout){
        AuthenticationEventEntity eventEntity = new AuthenticationEventEntity();
        eventEntity.setLoginTime(new Date());
        eventEntity.setLogout(false);
        eventEntity.setUserId(userDetail.getUserId());
        eventEntity.setTimeout(timeout);
        eventEntity.setUserType(userDetail.getUserType());
        eventEntity.setData(SecurityUtils.serialize(userDetail));

        return repository.save(eventEntity);
    }

    public void deleteAuthEvent(Long userId) {
        repository.updateLogoutUser(userId);
    }

    @Transactional
    public void setAuthenticationEventLogout(Long eventId){
        Optional<AuthenticationEventEntity> eventEntity = repository.findById(eventId);
        if (eventEntity.isPresent()) {
            eventEntity.get().setLogout(true);
            repository.save(eventEntity.get());
        }
    }

    @Transactional
    public void setAuthenticationEventLogoutByUserId(Integer userId, UserType userType){
        List<AuthenticationEventEntity> eventEntities = repository.findByUserIdAndUserType(userId,userType);
        for (AuthenticationEventEntity eventEntity : eventEntities){
            eventEntity.setLogout(true);
            repository.save(eventEntity);
        }
    }

    boolean isLogout(Long eventId){
        Optional<AuthenticationEventEntity> eventEntity = repository.findById(eventId);
        if (eventEntity.isPresent()) {
            return eventEntity.get().getLogout();
        }
        return false;
    }

    public Optional<AuthenticationEventEntity> findById(Long id) {
        LOGGER.info("Find AUTHENTICATION EVENT [{}] in db", id);
        return repository.findById(id);
    }
}
