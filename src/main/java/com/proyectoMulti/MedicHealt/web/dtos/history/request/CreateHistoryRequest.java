package com.proyectoMulti.MedicHealt.web.dtos.history.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateHistoryRequest {

    private String observation;
    @JsonProperty("id_patient") // Específica el nombre en snake_case para JSON
    private Long idPatient;


}
