package com.proyectoMulti.MedicHealt.service.serviceImpl;

import com.proyectoMulti.MedicHealt.web.dtos.auth.response.BaseResponse;
import com.proyectoMulti.MedicHealt.web.dtos.patient.request.CreatePatientRequest;
import com.proyectoMulti.MedicHealt.web.dtos.patient.request.UpdatePatientRequest;
import org.springframework.stereotype.Service;

@Service
public interface IPatientsService {

    public BaseResponse createPatient (CreatePatientRequest createPatientRequest);
    public BaseResponse getPatients();
    public BaseResponse getPatientById(Long id);
    public BaseResponse DeletePatientById(Long id);
    public BaseResponse UpdatePatientById(Long id , UpdatePatientRequest updatePatientRequest);


}
