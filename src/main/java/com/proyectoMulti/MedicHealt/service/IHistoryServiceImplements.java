package com.proyectoMulti.MedicHealt.service;

import com.proyectoMulti.MedicHealt.entity.History;
import com.proyectoMulti.MedicHealt.entity.Patient;
import com.proyectoMulti.MedicHealt.repository.HistoryRepository;
import com.proyectoMulti.MedicHealt.repository.PatientRepository;
import com.proyectoMulti.MedicHealt.service.serviceImpl.IHistoryService;
import com.proyectoMulti.MedicHealt.web.dtos.auth.response.BaseResponse;
import com.proyectoMulti.MedicHealt.web.dtos.history.request.CreateHistoryRequest;
import com.proyectoMulti.MedicHealt.web.dtos.history.response.HistoryResponse;
import com.proyectoMulti.MedicHealt.web.mappers.historyMapper.HistoyMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class IHistoryServiceImplements implements IHistoryService {

    HistoryRepository historyRepository;
    PatientRepository patientRepository;
    HistoyMappers histoyMappers;

    @Autowired
    public IHistoryServiceImplements(HistoryRepository historyRepository, HistoyMappers histoyMappers,PatientRepository patientRepository) {
        this.historyRepository = historyRepository;
        this.histoyMappers = histoyMappers;
        this.patientRepository = patientRepository;
    }
    @Override
    public BaseResponse createHistory(CreateHistoryRequest createHistoryRequest) {
        Optional<Patient> OptionalPatient = patientRepository.findById(createHistoryRequest.getIdPatient());
        if (OptionalPatient.isPresent()){

            Patient patient = OptionalPatient.get();
            History history = histoyMappers.CreateHistoryRequestToHistory(createHistoryRequest,patient);
            historyRepository.save(history);
            return BaseResponse.builder()
                    .message("Se creo Historial del Paciente: ")
                    .data(history)
                    .success(true)
                    .httpStatus(HttpStatus.OK)
                    .build();

        }else {
            return BaseResponse.builder()
                    .message("No se encontro al paciente ")
                    .data(null)
                    .success(false)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
    }

    @Override
    public BaseResponse getHistoryById(Long id) {


        Optional<History> Optionalhistory = historyRepository.findByPatientId(id);
        if (Optionalhistory.isPresent()){
            History history = Optionalhistory.get();

            HistoryResponse historyResponse = HistoryResponse.builder()
                    .date(history.getDate())
                    .patient(history.getPatient())
                    .biometricsDataList(history.getBiometricsDataList())
                    .build();


            return BaseResponse.builder()
                    .message("El historial del Paciente es: ")
                    .data(historyResponse)
                    .success(true)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }else {
            return BaseResponse.builder()
                    .message("No se encontro el historial del Paciente: ")
                    .data(null)
                    .success(false)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
    }

    @Override
    public BaseResponse DeleteHistoryById(Long id) {


        Optional<History> Optionalhistory = historyRepository.findById(id);
        if (Optionalhistory.isPresent()){
            History history = Optionalhistory.get();

            historyRepository.delete(history);

            return BaseResponse.builder()
                    .message("Se borro el historial del Paciente: ")
                    .data(history)
                    .success(true)
                    .httpStatus(HttpStatus.OK)
                    .build();


        }else {
            return BaseResponse.builder()
                    .message("No se encontro el historial del Paciente: ")
                    .data(null)
                    .success(false)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }    }


}
