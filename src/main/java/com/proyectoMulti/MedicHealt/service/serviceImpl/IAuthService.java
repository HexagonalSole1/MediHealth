package com.proyectoMulti.MedicHealt.service.serviceImpl;


import com.proyectoMulti.MedicHealt.web.dtos.auth.request.AuthenticationRequest;
import com.proyectoMulti.MedicHealt.web.dtos.auth.response.BaseResponse;

public interface IAuthService {

    BaseResponse authenticate(AuthenticationRequest request);
}
