package com.proyectoMulti.MedicHealt.web.dtos.sensores;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SensorHearthRequest {

    private float bpm;
    private float spo2;


}
