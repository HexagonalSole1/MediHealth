package com.proyectoMulti.MedicHealt.web.dtos.biometricsData.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBiometricDataResponse {

    private   Float oximeter;

    private Short heartbeat;

    private float temperature;


    @JsonProperty("name_patient") // Específica el nombre en snake_case para JSON
    private String namePatient;



}
