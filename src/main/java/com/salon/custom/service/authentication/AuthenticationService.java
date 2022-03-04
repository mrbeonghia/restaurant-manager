package com.salon.custom.service.authentication;

import com.salon.custom.dto.staff.StaffResponse;
import com.salon.custom.dto.user.UserResponse;
import com.salon.custom.entities.AuthenticationEventEntity;
import com.salon.custom.enums.UserType;
import com.salon.custom.exception.InvalidRefreshTokenException;
import com.salon.custom.security.CustomUserDetail;
import com.salon.custom.util.SecurityUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InvalidClassException;
import java.security.Key;
import java.util.*;

@Service
public class AuthenticationService {
    private static final String USER_ID = "USER_ID";
    private static final String AUTHENTICATION_ID = "authentication_id";
    private static final String TOKEN_TYPE = "token_type";
    private static final String REFRESH_TOKEN = "REFRESH_TOKEN";
    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";

    @Value("${application.access-token-validity}")
    private int accessTokenValidityInMinute;

    @Value("${application.refresh-token-validity}")
    private int refreshTokenValidityInMinute;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);

    private Key key;

    @Value("${application.secret}")
    private String secretTxt;

    @Autowired
    private AuthenticationEventService authenticationEventService;


    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secretTxt);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createRefreshToken(Authentication authentication) {
        CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();
        AuthenticationEventEntity authenticationEvent = authenticationEventService.create(userDetail, this.refreshTokenValidityInMinute);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + this.refreshTokenValidityInMinute);
        Date validity = calendar.getTime();

        Map<String, Object> claims = new HashMap<>();
        claims.put(USER_ID, userDetail.getUserId());
        claims.put(AUTHENTICATION_ID, authenticationEvent.getId());
        claims.put(TOKEN_TYPE, REFRESH_TOKEN);

        return Jwts.builder()
                .setSubject(userDetail.getUsername())
                .signWith(key, SignatureAlgorithm.HS512)
                .setClaims(claims)
                .setExpiration(validity)
                .compact();
    }

    public String createAccessToken(String refreshToken) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(refreshToken).getBody();
            claims.put(TOKEN_TYPE, ACCESS_TOKEN);
            Long authEventId = Long.valueOf(claims.get(AUTHENTICATION_ID).toString());
            if (authenticationEventService.isLogout(authEventId)) {
                throw new InvalidRefreshTokenException();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + this.accessTokenValidityInMinute);
            Date validity = calendar.getTime();
            LOGGER.info("Refresh access token success for event id [{}]", authEventId);
            return Jwts.builder()
                    .setSubject(claims.getSubject())
                    .signWith(key, SignatureAlgorithm.HS512)
                    .setClaims(claims)
                    .setExpiration(validity)
                    .compact();
        } catch (JwtException e) {
            throw new InvalidRefreshTokenException();
        }
    }

    public UserResponse createAccessTokenWhenRefresh(String refreshToken) {
        UserResponse userResponse = new UserResponse();
        try {
            LOGGER.info("Refresh with token: {}", refreshToken);
            String accessToken = createAccessToken(refreshToken);
            userResponse.setCode(4001);
            userResponse.setAccessToken(accessToken);
            return userResponse;
        } catch (InvalidRefreshTokenException e) {
            LOGGER.info("Error createAccessTokenWhenRefresh: [{}]", e.getMessage());
            LOGGER.error(e.toString());
            userResponse.setSuccess(false);
            userResponse.setCode(4001);
            return userResponse;
        }
    }

    public TokenVerifyResult verifyAccessToken(String accessToken) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
            Long authEventId = Long.valueOf(claims.get(AUTHENTICATION_ID).toString());
            String tokenType = claims.get(TOKEN_TYPE).toString();
            Optional<AuthenticationEventEntity> authEvent = authenticationEventService.findById(authEventId);
            if (authEvent.isEmpty()) {
                return new TokenVerifyResult(false);
            }

            boolean valid = !authEvent.get().getLogout() && tokenType.equalsIgnoreCase(ACCESS_TOKEN);
            TokenVerifyResult result = new TokenVerifyResult(valid);

            if (valid) {
                result.setAuthEventId(authEventId);
                result.setUserDetail(SecurityUtils.deserialize(authEvent.get().getData()));
            }

            return result;
        } catch (JwtException | InvalidClassException e) {
            return new TokenVerifyResult(false);
        }
    }

    public UserResponse logout(String refreshToken, String deviceId) {
        UserResponse userResponse = new UserResponse();
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(refreshToken).getBody();
            Long authEventId = Long.valueOf(claims.get(AUTHENTICATION_ID).toString());
            String tokenType = claims.get(TOKEN_TYPE).toString();

            if (tokenType.equalsIgnoreCase(REFRESH_TOKEN)) {
                authenticationEventService.setAuthenticationEventLogout(authEventId);
            } else {
                throw new InvalidRefreshTokenException();
            }
            return userResponse;
        } catch (JwtException e) {
            throw new InvalidRefreshTokenException();
        }
    }

    public StaffResponse staffLogout(String refreshToken, String deviceId) {
        StaffResponse staffResponse = new StaffResponse();
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(refreshToken).getBody();
            Long authEventId = Long.valueOf(claims.get(AUTHENTICATION_ID).toString());
            String tokenType = claims.get(TOKEN_TYPE).toString();
            if (tokenType.equalsIgnoreCase(REFRESH_TOKEN)) {
                authenticationEventService.setAuthenticationEventLogout(authEventId);
            } else {
                throw new InvalidRefreshTokenException();
            }
            return staffResponse;
        } catch (JwtException e) {
            throw new InvalidRefreshTokenException();
        }
    }

    public void logoutByUserId(Integer userId, UserType userType) {
        authenticationEventService.setAuthenticationEventLogoutByUserId(userId, userType);
    }


}
