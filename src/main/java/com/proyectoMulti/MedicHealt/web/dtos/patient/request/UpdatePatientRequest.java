package com.proyectoMulti.MedicHealt.web.dtos.patient.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePatientRequest {

    String curp;

    String name;

    @JsonProperty("last_name") // Específica el nombre en snake_case para JSON
    String lastName;

    int weight;

    int height;

    short imc;
    String gender;
    short edad;

}
