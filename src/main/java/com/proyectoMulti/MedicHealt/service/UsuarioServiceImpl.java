package com.proyectoMulti.MedicHealt.service;


import com.proyectoMulti.MedicHealt.entity.Users;
import com.proyectoMulti.MedicHealt.repository.PersonalRepository;
import com.proyectoMulti.MedicHealt.service.serviceImpl.IUsuarioService;
import com.proyectoMulti.MedicHealt.web.dtos.Users.CreateUsuarioRequest;
import com.proyectoMulti.MedicHealt.web.dtos.auth.response.BaseResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    PersonalRepository usuarioRepository;
    @Override
    @Transactional  // Agrega esta anotación para indicar que este método debe ejecutarse dentro de una transacción
    public BaseResponse getUsuariosById (Long id){


        Optional<Users> response = usuarioRepository.findById(id);

        if (!response.isEmpty()) {
            return BaseResponse.builder()
                    .data(response)
                    .message("Se encontro el Usuario:")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
        else{
            return BaseResponse.builder()
                    .data(response)
                    .message("No se encontro al Usuario")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();

        }

    }

    @Override
    @Transactional
    public BaseResponse deletedById(Long id) {


        List<Users> response = usuarioRepository.getUsuarioById(id);

        usuarioRepository.deleteUsuarioById(id, new Date());


        if (!response.isEmpty()) {
            return BaseResponse.builder()
                    .data(null)
                    .message("Usuario a sido Eliminado")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();



        }
        return BaseResponse.builder()
                .data(null)
                .message("Usuario no encontrado")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    @Transactional  // Agrega esta anotación para indicar que este método debe ejecutarse dentro de una transacción
    public BaseResponse createUsuario(CreateUsuarioRequest request) {

            Optional<Users> OptionalUsers =usuarioRepository.findByEmail(request.getEmail());

        if (!OptionalUsers.isPresent()){

            Users users = Users.builder()
                    .name(request.getNombre())
                    .lastName(request.getApellido())
                    .password( encodePassword(request.getPassword()))
                    .rol(request.getRol())
                    .email(request.getEmail())
                    .build();

                Users userSalvado = usuarioRepository.save(users);
            return BaseResponse.builder()
                    .data(userSalvado)
                    .message("Usuario Creado")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.CREATED)
                    .build();

        }
        else {
            return BaseResponse.builder()
                    .data(null)
                    .message("Ya hay un usuario con ese correo")
                    .success(Boolean.FALSE)
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();

        }

    }


    private static String encodePassword(String request){
        return new BCryptPasswordEncoder().encode(request);
    }

    @Override
    @Transactional
    public BaseResponse getUsuario(int page, int size) {
        int offset = (page-1) * size;



        List<Users> response = usuarioRepository.getUsuarios(offset, size);
        int totalUsuarios = usuarioRepository.getTotalUsuarios();  // Asegúrate de tener este método en tu repositorio

        if (!response.isEmpty()) {
            return BaseResponse.builder()
                    .data(response)
                    .message("Se encontraron los siguientes Usuarios:")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        } else {
            return BaseResponse.builder()
                    .data(response)
                    .message("No se encontraron Usuarios")
                    .success(Boolean.FALSE)  // Corregir a Boolean.FALSE
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
    }



    @Override
    @Transactional
    public BaseResponse updateUsuario(Long id, CreateUsuarioRequest request) {
        Optional<Users> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()) {
            Users usuarioActual = optionalUsuario.get();
            Date updatedAt = new Date();

            Users usuarioActualizado = combinarUsuarioConRequest(usuarioActual, request);

            usuarioRepository.updateUsuario(
                    id,
                    usuarioActualizado.getName(),
                    usuarioActualizado.getLastName(),
                    usuarioActualizado.getEmail(),
                    encodePassword(  usuarioActualizado.getPassword()),
                    usuarioActualizado.getRol(),
                    updatedAt
            );

            return BaseResponse.builder()
                    .data(null)
                    .message("Usuario Modificado")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        } else {
            return BaseResponse.builder()
                    .data(null)
                    .message("Usuario no encontrado")
                    .success(Boolean.FALSE)
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    private Users combinarUsuarioConRequest(Users usuario, CreateUsuarioRequest request) {
        Users usuarioActualizado = new Users();
        BeanUtils.copyProperties(usuario, usuarioActualizado, getNullPropertyNames(usuario));
        BeanUtils.copyProperties(request, usuarioActualizado, getNullPropertyNames(request));
        return usuarioActualizado;
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }



}





