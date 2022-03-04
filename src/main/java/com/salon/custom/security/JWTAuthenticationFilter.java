package com.salon.custom.security;

import com.salon.custom.service.authentication.AuthenticationService;
import com.salon.custom.service.authentication.TokenVerifyResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthenticationFilter implements Filter {

    public static final String ACCESS_TOKEN_PARAM = "Authorization";
    public static final String ACCESS_TOKEN_PREFIX = "Bearer ";

    @Autowired
    private AuthenticationService authenticationService;

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String accessToken = request.getHeader(ACCESS_TOKEN_PARAM);

        LOGGER.info("Verify token for request {}", request.getRequestURI());

        if (!StringUtils.isEmpty(accessToken)) {
            LOGGER.info("Token found {}", accessToken);

            accessToken = accessToken.replace(ACCESS_TOKEN_PREFIX, "");
            TokenVerifyResult verifyResult = authenticationService.verifyAccessToken(accessToken);

            if (verifyResult.isValid()) {
                LOGGER.info("Verify access token success.");
                setUserAuthenticatedManually(verifyResult.getUserDetail(), accessToken);
            } else {
                LOGGER.info("Verify access token fail.");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } else {
            LOGGER.info("No token found");
        }

        chain.doFilter(servletRequest, servletResponse);
    }

    private void setUserAuthenticatedManually(CustomUserDetail userDetails, String accessToken) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, accessToken, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
