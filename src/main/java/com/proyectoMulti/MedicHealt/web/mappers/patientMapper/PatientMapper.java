package com.proyectoMulti.MedicHealt.web.mappers.patientMapper;

import com.proyectoMulti.MedicHealt.entity.Patient;
import com.proyectoMulti.MedicHealt.web.dtos.patient.request.CreatePatientRequest;
import com.proyectoMulti.MedicHealt.web.dtos.patient.response.CreatePatientResponse;
import com.proyectoMulti.MedicHealt.web.dtos.patient.response.FindPatientResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PatientMapper {


    public Patient CreatePatientRequestToPatient(CreatePatientRequest createPatientRequest){

        Patient patient = Patient.builder()
                .name(createPatientRequest.getName())
                .lastName(createPatientRequest.getLastName())
                .weight(createPatientRequest.getWeight())
                .height(createPatientRequest.getHeight())
                .curp(createPatientRequest.getCurp())
                .imc(createPatientRequest.getImc())
                .edad(createPatientRequest.getEdad())
                .gender(createPatientRequest.getGender())
                .build();

        return patient;
    }
    public CreatePatientResponse PatientToCreatePatientResponse(Patient patient){

        CreatePatientResponse patientResponse = CreatePatientResponse.builder()
                .name(patient.getName())
                .lastName(patient.getLastName())
                .build();

        return patientResponse;
    }
    public FindPatientResponse OptionalPatientToFindPatientResponse (Optional<Patient> optionalPatient){

        if (optionalPatient.isPresent()){

            Patient patient = optionalPatient.get();
            FindPatientResponse findPatientResponse = FindPatientResponse.builder()
                    .id(patient.getId())
                    .name(patient.getName())
                    .lastName(patient.getLastName())
                    .curp(patient.getCurp())
                    .height(patient.getHeight())
                    .weight(patient.getWeight())
                    .imc(patient.getImc())
                    .edad(patient.getEdad())
                    .gender(patient.getGender())
                    .build();

            return findPatientResponse;
        }else {
            return null;
        }

    }



}
