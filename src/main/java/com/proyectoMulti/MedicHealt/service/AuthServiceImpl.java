package com.proyectoMulti.MedicHealt.service;



import com.proyectoMulti.MedicHealt.security.JWTUtils;
import com.proyectoMulti.MedicHealt.service.ServiceConfig.UserDetailsImpl;
import com.proyectoMulti.MedicHealt.service.serviceImpl.IAuthService;
import com.proyectoMulti.MedicHealt.web.dtos.auth.request.AuthenticationRequest;
import com.proyectoMulti.MedicHealt.web.dtos.auth.response.BaseResponse;
import com.proyectoMulti.MedicHealt.web.dtos.auth.response.responseToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final AuthenticationManager authenticationManager;

    @Value("${devmov.app.jwtSecret}")
    private String jwtSecret;

    @Override
    public BaseResponse authenticate(AuthenticationRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String email = userDetails.getUsername();

        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", userDetails.getUser().getId());
        String token = JWTUtils.generateToken(jwtSecret, email, payload);
        long id = Long.parseLong(String.valueOf(userDetails.getUser().getId()));


        responseToken datoUsuario =  responseToken.builder().rol(String.valueOf(userDetails.getUser().getRoles()))
                .token(token).id(id).build();

        return BaseResponse.builder()
                .data(datoUsuario)
                .message("Se a accedido correctamente")
                .httpStatus(HttpStatus.CREATED)
                .success(Boolean.TRUE)
                .build();
    }
}
