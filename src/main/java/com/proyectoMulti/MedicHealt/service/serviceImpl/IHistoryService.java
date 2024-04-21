package com.proyectoMulti.MedicHealt.service.serviceImpl;


import com.proyectoMulti.MedicHealt.web.dtos.auth.response.BaseResponse;
import com.proyectoMulti.MedicHealt.web.dtos.history.request.CreateHistoryRequest;
import com.proyectoMulti.MedicHealt.web.dtos.patient.request.CreatePatientRequest;
import com.proyectoMulti.MedicHealt.web.dtos.patient.request.UpdatePatientRequest;
import org.springframework.stereotype.Service;

@Service
public interface IHistoryService {

    BaseResponse createHistory(CreateHistoryRequest createHistoryRequest);

    public BaseResponse getHistoryById(Long id);
    public BaseResponse DeleteHistoryById(Long id);



}
