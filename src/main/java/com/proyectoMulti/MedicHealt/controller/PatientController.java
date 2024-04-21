package com.proyectoMulti.MedicHealt.controller;


import com.proyectoMulti.MedicHealt.service.serviceImpl.IPatientsService;
import com.proyectoMulti.MedicHealt.web.dtos.auth.response.BaseResponse;
import com.proyectoMulti.MedicHealt.web.dtos.patient.request.CreatePatientRequest;
import com.proyectoMulti.MedicHealt.web.dtos.patient.request.UpdatePatientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {


    IPatientsService patientsService;

    @Autowired
    public PatientController(IPatientsService patientsService) {
       this.patientsService = patientsService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createPatient(@RequestBody CreatePatientRequest createPatientRequest){
        BaseResponse baseResponse = patientsService.createPatient(createPatientRequest);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }


    @GetMapping()
    public ResponseEntity<BaseResponse> getPatients(){
        BaseResponse baseResponse = patientsService.getPatients();
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<BaseResponse> getPatientsById(@PathVariable Long id){
        BaseResponse baseResponse = patientsService.getPatientById(id);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<BaseResponse> DeletePatientById(@PathVariable Long id){
        BaseResponse baseResponse = patientsService.DeletePatientById(id);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }
    @PatchMapping(path="/{id}")
    public ResponseEntity<BaseResponse> UpdatePatientById(@PathVariable Long id, @RequestBody UpdatePatientRequest updatePatientRequest){
        BaseResponse baseResponse = patientsService.UpdatePatientById(id,updatePatientRequest);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }


}
