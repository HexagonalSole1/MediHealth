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
public class CreatePatientResponse {

    String name;

    @JsonProperty("last_name")
    String lastName;




}
