package com.proyectoMulti.MedicHealt.service.serviceImpl;

import com.proyectoMulti.MedicHealt.web.dtos.Users.CreateUsuarioRequest;
import com.proyectoMulti.MedicHealt.web.dtos.auth.response.BaseResponse;
import jakarta.transaction.Transactional;

public interface IUsuarioService {
    @Transactional  // Agrega esta anotación para indicar que este método debe ejecutarse dentro de una transacción
    BaseResponse deletedById(Long id);

    @Transactional
        // Agrega esta anotación para indicar que este método debe ejecutarse dentro de una transacción
    BaseResponse createUsuario(CreateUsuarioRequest request);

    @Transactional  // Agrega esta anotación para indicar que este método debe ejecutarse dentro de una transacción
    BaseResponse getUsuario(int page, int size);

    @Transactional  // Agrega esta anotación para indicar que este método debe ejecutarse dentro de una transacción
    BaseResponse getUsuariosById(Long id);

    @Transactional  // Agrega esta anotación para indicar que este método debe ejecutarse dentro de una transacción
    BaseResponse updateUsuario(Long id, CreateUsuarioRequest request);
}
