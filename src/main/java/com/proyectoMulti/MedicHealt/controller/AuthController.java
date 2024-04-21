package com.proyectoMulti.MedicHealt.controller;

import com.proyectoMulti.MedicHealt.service.serviceImpl.IAuthService;
import com.proyectoMulti.MedicHealt.web.dtos.auth.request.AuthenticationRequest;
import com.proyectoMulti.MedicHealt.web.dtos.auth.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

/*    @PostMapping("token")
    public ResponseEntity<BaseResponse> authentication (@RequestBody AuthenticationRequest request){
       // return service.authenticate(request).apply();

        return "hola mi compa";
    }*/

    @Autowired
    private IAuthService service;

    @PostMapping("token")
    public ResponseEntity<BaseResponse> authentication (@RequestBody AuthenticationRequest request){
        return service.authenticate(request).apply();
    }

}
