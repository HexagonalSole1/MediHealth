package com.proyectoMulti.MedicHealt.web.dtos.patient.request;


import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePatientRequest {

    String curp;

    String name;

    @JsonProperty("last_name") // Específica el nombre en snake_case para JSON
    String lastName;

    int weight;

    int height;

    short imc;
    String gender;

    short edad;

    private String observation;

    @JsonProperty("id_patient") // Específica el nombre en snake_case para JSON
    private Long idPatient;
}
