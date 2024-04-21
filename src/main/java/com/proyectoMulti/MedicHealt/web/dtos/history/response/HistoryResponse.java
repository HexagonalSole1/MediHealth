package com.proyectoMulti.MedicHealt.web.dtos.history.response;

import com.proyectoMulti.MedicHealt.entity.BiometricsData;
import com.proyectoMulti.MedicHealt.entity.Patient;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoryResponse {


    private String observation;

    private Date date;



    private Patient patient;


    private List<BiometricsData> biometricsDataList;
}
