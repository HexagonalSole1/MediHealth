package com.proyectoMulti.MedicHealt.service;

import com.proyectoMulti.MedicHealt.entity.History;
import com.proyectoMulti.MedicHealt.entity.Patient;
import com.proyectoMulti.MedicHealt.repository.HistoryRepository;
import com.proyectoMulti.MedicHealt.repository.PatientRepository;
import com.proyectoMulti.MedicHealt.service.serviceImpl.IHistoryService;
import com.proyectoMulti.MedicHealt.service.serviceImpl.IPatientsService;
import com.proyectoMulti.MedicHealt.web.dtos.auth.response.BaseResponse;
import com.proyectoMulti.MedicHealt.web.dtos.history.request.CreateHistoryRequest;
import com.proyectoMulti.MedicHealt.web.dtos.patient.request.CreatePatientRequest;
import com.proyectoMulti.MedicHealt.web.dtos.patient.request.UpdatePatientRequest;
import com.proyectoMulti.MedicHealt.web.dtos.patient.response.CreatePatientResponse;
import com.proyectoMulti.MedicHealt.web.dtos.patient.response.FindPatientResponse;
import com.proyectoMulti.MedicHealt.web.mappers.patientMapper.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.awt.print.Paper;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


@Service()
public class IPatientsServiceImplements implements IPatientsService {
    PatientRepository patientRepository;
    PatientMapper patientMapper;

    IHistoryService iHistoryService;

    HistoryRepository historyRepository;

    @Autowired
    public IPatientsServiceImplements(PatientRepository patientRepository, PatientMapper patientMapper, IHistoryService iHistoryService, HistoryRepository historyRepository) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.iHistoryService = iHistoryService;
        this.historyRepository = historyRepository;
    }

    @Override
    public BaseResponse createPatient(CreatePatientRequest createPatientRequest) {
        Patient patient = patientMapper.CreatePatientRequestToPatient(createPatientRequest);


        Patient patientSave = patientRepository.save(patient);

        History history = History.builder()
                .date(Date.valueOf(LocalDate.now()))
                .observation(createPatientRequest.getObservation())
                .patient(patientSave)
                .build();
        historyRepository.save(history);

       CreatePatientResponse createPatientResponse= patientMapper.PatientToCreatePatientResponse(patient);

        BaseResponse baseResponse = BaseResponse.builder()
                .data(createPatientResponse)
                .message("Se ha creado exitosamente el paciente")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();
        return baseResponse;
    }

    @Override
    public BaseResponse getPatients() {
        ArrayList<Patient> ListPatients = (ArrayList<Patient>) patientRepository.findAll();


        BaseResponse baseResponse = BaseResponse.builder()
                .message("La lista de pacientes es : ")
                .data(ListPatients)
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();
        return baseResponse;
    }

    @Override
    public BaseResponse getPatientById(Long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        BaseResponse baseResponse;
        if (optionalPatient.isPresent()){
            FindPatientResponse findPatientResponse = patientMapper.OptionalPatientToFindPatientResponse(optionalPatient);

            baseResponse = BaseResponse.builder()
                    .message("Se encontró al siguiente paciente: ")
                    .data(findPatientResponse)
                    .success(true)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }else {
            baseResponse = BaseResponse.builder()
                    .message("No se encontró el paciente")
                    .data(null)
                    .success(false)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
        return baseResponse;
    }

    @Override
    public BaseResponse DeletePatientById(Long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
       Optional<History> optionalHistory= historyRepository.findByPatientId(id);
        BaseResponse baseResponse;
        if (optionalPatient.isPresent() && optionalHistory.isPresent()){


            FindPatientResponse findPatientResponse = patientMapper.OptionalPatientToFindPatientResponse(optionalPatient);
            Patient patient = optionalPatient.get();
            historyRepository.delete(optionalHistory.get());
            baseResponse = BaseResponse.builder()
                    .message("Se elimino el Siguiente Paciente: ")
                    .data(findPatientResponse)
                    .success(true)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }else {
            baseResponse = BaseResponse.builder()
                    .message("No se encontró el paciente")
                    .data(null)
                    .success(false)
                    .httpStatus(HttpStatus.NO_CONTENT)
                    .build();
        }
        return baseResponse;
    }
    @Override
    public BaseResponse UpdatePatientById(Long id, UpdatePatientRequest updatePatientRequest) {
        BaseResponse baseResponse;
        if (updatePatientRequest == null) {
            baseResponse = BaseResponse.builder()
                    .message("La solicitud de actualización es nula")
                    .data(null)
                    .success(false)
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
            return baseResponse;
        }

        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setName(updatePatientRequest.getName());
                    patient.setLastName(updatePatientRequest.getLastName());
                    patient.setCurp(updatePatientRequest.getCurp());
                    patient.setImc(updatePatientRequest.getImc());
                    patient.setHeight(updatePatientRequest.getHeight());
                    patient.setWeight(updatePatientRequest.getWeight());
                    patient.setEdad(updatePatientRequest.getEdad());
                    patient.setGender(updatePatientRequest.getGender());
                    patientRepository.save(patient);

                    FindPatientResponse findPatientResponse = FindPatientResponse.builder()
                            .name(updatePatientRequest.getName())
                            .lastName(updatePatientRequest.getLastName())
                            .curp(updatePatientRequest.getCurp())
                            .imc(updatePatientRequest.getImc())
                            .height(updatePatientRequest.getHeight())
                            .weight(updatePatientRequest.getWeight())
                            .gender(updatePatientRequest.getGender())
                            .edad(updatePatientRequest.getEdad())
                            .build();

                    return BaseResponse.builder()
                            .message("Se modificó el siguiente paciente: ")
                            .data(findPatientResponse)
                            .success(true)
                            .httpStatus(HttpStatus.OK)
                            .build();
                })
                .orElseGet(() -> BaseResponse.builder()
                        .message("No se encontró el paciente con el ID: " + id)
                        .data(null)
                        .success(false)
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .build());
    }



}
