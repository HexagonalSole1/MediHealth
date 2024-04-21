package com.proyectoMulti.MedicHealt.controller;


import com.proyectoMulti.MedicHealt.service.serviceImpl.IBiometricsDataService;
import com.proyectoMulti.MedicHealt.service.serviceImpl.IHistoryService;
import com.proyectoMulti.MedicHealt.web.dtos.auth.response.BaseResponse;
import com.proyectoMulti.MedicHealt.web.dtos.biometricsData.request.CreateBiometricData;
import com.proyectoMulti.MedicHealt.web.dtos.patient.request.CreatePatientRequest;
import com.proyectoMulti.MedicHealt.web.dtos.patient.request.UpdatePatientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("BiometricData")
public class BiometricsDataController {
    IBiometricsDataService iBiometricsDataService;

    @Autowired
    public BiometricsDataController(IBiometricsDataService iBiometricsDataService) {
        this.iBiometricsDataService = iBiometricsDataService;
    }


    @PostMapping
    public ResponseEntity<BaseResponse> createBiometricData(@RequestBody CreateBiometricData createBiometricData){
        BaseResponse baseResponse = iBiometricsDataService.createBiometricsData(createBiometricData);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }


   /* @GetMapping(path="/{id}")
    public ResponseEntity<BaseResponse> getPatientsById(@PathVariable Long id){
        BaseResponse baseResponse = iHistoryService.getHistoryById(id);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<BaseResponse> DeletePatientById(@PathVariable Long id){
        BaseResponse baseResponse = iHistoryService.DeleteHistoryById(id);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }
    @PatchMapping(path="/{id}")
    public ResponseEntity<BaseResponse> UpdatePatientById(@PathVariable Long id, @RequestBody UpdatePatientRequest updatePatientRequest){
        BaseResponse baseResponse = iHistoryService.UpdateHistoryById(id,updatePatientRequest);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }*/


}
