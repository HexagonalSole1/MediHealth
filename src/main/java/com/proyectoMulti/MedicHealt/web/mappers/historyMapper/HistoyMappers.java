package com.proyectoMulti.MedicHealt.web.mappers.historyMapper;


import com.proyectoMulti.MedicHealt.entity.History;
import com.proyectoMulti.MedicHealt.entity.Patient;
import com.proyectoMulti.MedicHealt.web.dtos.history.request.CreateHistoryRequest;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
public class HistoyMappers {
    public History CreateHistoryRequestToHistory(CreateHistoryRequest createHistoryRequest, Patient patient){



        History history = History.builder()
                .date(Date.valueOf(LocalDate.now()))
                .observation(createHistoryRequest.getObservation())
                .patient(patient)
                .build();
        return history;


    }
}
