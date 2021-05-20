package com.w2m.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.w2m.exception.APIErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
        response.addHeader("content-type", "application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        APIErrorResponse error = new APIErrorResponse("unauthenticated", "HTTP Status 401 - " + authException.getMessage());
        writer.println(objectMapper.writeValueAsString(error));
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("heroes-api");
        super.afterPropertiesSet();
    }
}