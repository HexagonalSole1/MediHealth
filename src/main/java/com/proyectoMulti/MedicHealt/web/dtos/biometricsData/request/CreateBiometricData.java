package com.proyectoMulti.MedicHealt.web.dtos.biometricsData.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyectoMulti.MedicHealt.entity.History;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBiometricData {


    private   Float oximeter;

    private Short heartbeat;

    private float temperature;


    @JsonProperty("id_patient") // Específica el nombre en snake_case para JSON
    private Long idPatient;

}
