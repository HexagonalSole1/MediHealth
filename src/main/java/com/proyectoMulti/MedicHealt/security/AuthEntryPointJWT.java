package com.proyectoMulti.MedicHealt.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectoMulti.MedicHealt.web.dtos.auth.response.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.io.OutputStream;

@Component
public class AuthEntryPointJWT implements AuthenticationEntryPoint {

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        OutputStream responseStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();

        BaseResponse baseResponse = BaseResponse.builder()
                .message(authException.getLocalizedMessage())
                .success(Boolean.FALSE)
                .build();

        // Usar el objeto baseResponse al escribir en el flujo de respuesta
        mapper.writeValue(responseStream, baseResponse);
        responseStream.flush();
    }

}
