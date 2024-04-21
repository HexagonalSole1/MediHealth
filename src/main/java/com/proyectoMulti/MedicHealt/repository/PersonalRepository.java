package com.proyectoMulti.MedicHealt.repository;

import com.proyectoMulti.MedicHealt.entity.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface PersonalRepository extends CrudRepository<Users,Long> {
    Optional<Users> findByEmail(String username);

    @Modifying
    @Query(value = "INSERT INTO users (name, last_name, email, password, rol) VALUES (:nombre, :apellido, :email, :password, :rol)", nativeQuery = true)
    void createUsuario(
            @Param("nombre") String nombre,
            @Param("apellido") String apellido,
            @Param("email") String email,
            @Param("password") String password,
            @Param("rol") String rol
    );

    @Query(value = "SELECT COUNT(email) FROM users WHERE email = :email", nativeQuery = true)
    int existeUsuario(@Param("email") String email);



    @Modifying
    @Query(value = "UPDATE usuario SET deleted = 1, deleted_at = :deletedAt WHERE clienteId = :clienteId AND deleted = 0", nativeQuery = true)
    void deleteUsuarioById(
            @Param("clienteId") Long clienteId,
            @Param("deletedAt") Date deletedAt
    );
    @Query(value = "SELECT * FROM usuario WHERE deleted = 0 LIMIT :offset, :limit", nativeQuery = true)
    List<Users> getUsuarios(@Param("offset") int offset, @Param("limit") int limit);
    @Query(value = "SELECT COUNT(*) FROM usuario WHERE deleted = 0", nativeQuery = true)
    int getTotalUsuarios();
    @Query(value = "SELECT * FROM usuario WHERE clienteId = :clienteId AND deleted = 0", nativeQuery = true)
    List<Users> getUsuarioById(@Param("clienteId") Long clienteId);


    @Modifying
    @Query(value = "UPDATE usuario SET nombre = :nombre, apellido = :apellido, email = :email, password = :password, rol = :rol, updated_at = :updatedAt WHERE clienteID = :clienteId and deleted=0", nativeQuery = true)
    void updateUsuario(
            @Param("clienteId") Long clienteId,
            @Param("nombre") String nombre,
            @Param("apellido") String apellido,
            @Param("email") String email,
            @Param("password") String password,
            @Param("rol") String rol,
            @Param("updatedAt") Date updatedAt
    );



}
