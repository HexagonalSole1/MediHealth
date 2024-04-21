package com.proyectoMulti.MedicHealt.web.dtos.auth.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class responseToken {

    private String token;
    private String rol;
    private Long id;
}
