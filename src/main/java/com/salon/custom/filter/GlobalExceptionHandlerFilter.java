package com.salon.custom.filter;


import com.salon.base.email.EmailService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

@Component
public class GlobalExceptionHandlerFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandlerFilter.class);

    @Autowired
    private EmailService emailService;

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            if ("org.apache.catalina.connector.ClientAbortException".equals(e.getClass().getName())) {
                LOGGER.info("Client abort request!!");
            } else {
                LOGGER.error(e.getMessage());
                LOGGER.error("Sending error report mail");
                HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                sendErrorReport(ExceptionUtils.getStackTrace(e), httpServletRequest);

                if (!httpServletRequest.getServletPath().startsWith("/api/")) {
                    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                    httpServletResponse.sendRedirect("/errorPage");
                } else {
                    throw e;
                }
            }
        }
    }

    private void sendErrorReport(String errorMessage, HttpServletRequest request) {
        DateFormatter dateFormatter = new DateFormatter("dd-MM-yyyy HH:mm");
        String subject = " SALON ERROR REPORT | " + dateFormatter.print(new Date(), request.getLocale());

        StringBuilder sb = new StringBuilder();
        sb.append("<p style = 'font-weight: bold'>").append("Current URL: ").append(request.getRequestURL().toString()).append("</p>").append("<br />");
        sb.append("<p style = 'font-weight: bold'>").append("Access-token: ").append(request.getHeader("Authorization")).append("</p>").append("<br />");


        sb.append("<p style = 'font-weight: bold'>").append("Request Info: ").append(getRequestInfo(request) + "</p>");

        errorMessage = errorMessage.replaceAll("\n", "<br />");
        sb.append(errorMessage);

        emailService.sendEmail(subject, sb.toString());
    }

    private String getRequestParams(HttpServletRequest request) {
        StringBuilder params = new StringBuilder();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            params.append(paramName + ":" + request.getParameter(paramName));
            params.append("<br />");
        }
        return params.toString();
    }

    private String getRequestInfo(HttpServletRequest request) {
        StringBuilder requestInfo = new StringBuilder();
        requestInfo.append("<br />Request INFO: ");
        requestInfo.append("<br />");
        requestInfo.append("URI: " + request.getRequestURI());
        requestInfo.append("<br />");
        requestInfo.append("Method: " + request.getMethod());
        requestInfo.append("<br />");
        requestInfo.append("Content-Type: " + request.getContentType());
        requestInfo.append("<br />");
//        String body = new String(request.getContentAsByteArray());
        requestInfo.append("Request Params: ");
        String requestParams = getRequestParams(request);
        if (requestParams.length() > 0) {
            requestInfo.append("<dd>");
            requestInfo.append(requestParams);
            requestInfo.append("</dd>");
        }
//        requestInfo.append("<p style = 'font-weight: bold'>").append("Request Body: " + body);
        requestInfo.append("<br />");
        return requestInfo.toString();
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        LOGGER.info("Init Catching exception filter.");
    }

}
