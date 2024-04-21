package com.proyectoMulti.MedicHealt.service;

import com.proyectoMulti.MedicHealt.entity.BiometricsData;
import com.proyectoMulti.MedicHealt.entity.History;
import com.proyectoMulti.MedicHealt.entity.Patient;
import com.proyectoMulti.MedicHealt.repository.BiometricsDataRepository;
import com.proyectoMulti.MedicHealt.repository.HistoryRepository;
import com.proyectoMulti.MedicHealt.repository.PatientRepository;
import com.proyectoMulti.MedicHealt.service.serviceImpl.IBiometricsDataService;
import com.proyectoMulti.MedicHealt.web.dtos.auth.response.BaseResponse;
import com.proyectoMulti.MedicHealt.web.dtos.biometricsData.request.CreateBiometricData;
import com.proyectoMulti.MedicHealt.web.dtos.biometricsData.response.CreateBiometricDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IBiometricsDataServiceImplements  implements IBiometricsDataService {
    PatientRepository patientRepository;
    HistoryRepository historyRepository;
    BiometricsDataRepository biometricsDataRepository;

    @Autowired
    public IBiometricsDataServiceImplements(PatientRepository patientRepository, HistoryRepository historyRepository, BiometricsDataRepository biometricsDataRepository) {
        this.patientRepository = patientRepository;
        this.historyRepository = historyRepository;
        this.biometricsDataRepository = biometricsDataRepository;
    }

    @Override
    public BaseResponse createBiometricsData(CreateBiometricData createBiometricData) {
        Optional<Patient> OptionalPatient = patientRepository.findById(createBiometricData.getIdPatient());
        if (OptionalPatient.isPresent()){
            Patient patient = OptionalPatient.get();
           Optional<History> optionalHistory= historyRepository.findByPatientId(createBiometricData.getIdPatient());

           if (optionalHistory.isPresent()){

               History history = optionalHistory.get();
               BiometricsData biometricsData= BiometricsData.builder()
                       .heartbeat(createBiometricData.getHeartbeat())
                       .oximeter(createBiometricData.getOximeter())
                       .temperature(createBiometricData.getTemperature())
                       .history(history)
                       .build();

               biometricsDataRepository.save(biometricsData);

               CreateBiometricDataResponse createBiometricDataResponse= CreateBiometricDataResponse.builder()
                       .heartbeat(createBiometricData.getHeartbeat())
                       .temperature(createBiometricData.getTemperature())
                       .oximeter(createBiometricData.getOximeter())
                       .namePatient(patient.getName() +" "+patient.getLastName())
                       .build();


               return BaseResponse.builder()
                       .message("Se creo añadió los datos biometricos al historial del Paciente")
                       .data(createBiometricDataResponse)
                       .success(true)
                       .httpStatus(HttpStatus.OK)
                       .build();
           }else {
               return BaseResponse.builder()
                       .message("No se encontro el historial del paciente")
                       .data(null)
                       .success(false)
                       .httpStatus(HttpStatus.OK)
                       .build();
           }


        }else {
            return BaseResponse.builder()
                    .message("No se encontro al paciente ")
                    .data(null)
                    .success(false)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
    }
}
