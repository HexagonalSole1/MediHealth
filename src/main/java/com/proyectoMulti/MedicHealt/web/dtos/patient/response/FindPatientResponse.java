package com.proyectoMulti.MedicHealt.web.dtos.patient.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyectoMulti.MedicHealt.entity.BiometricsData;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindPatientResponse {

    Long id;

    String name;

    @JsonProperty("last_name")
    String lastName;

    int weight;

    int height;

    short imc;

    String curp;

    String gender;
    short edad;


}
