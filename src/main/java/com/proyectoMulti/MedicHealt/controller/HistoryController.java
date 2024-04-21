package com.proyectoMulti.MedicHealt.controller;


import com.proyectoMulti.MedicHealt.service.serviceImpl.IHistoryService;
import com.proyectoMulti.MedicHealt.service.serviceImpl.IPatientsService;
import com.proyectoMulti.MedicHealt.web.dtos.auth.response.BaseResponse;
import com.proyectoMulti.MedicHealt.web.dtos.history.request.CreateHistoryRequest;
import com.proyectoMulti.MedicHealt.web.dtos.patient.request.CreatePatientRequest;
import com.proyectoMulti.MedicHealt.web.dtos.patient.request.UpdatePatientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/history")
public class HistoryController {

    IHistoryService iHistoryService;
    @Autowired
    public HistoryController(IHistoryService iHistoryService) {
        this.iHistoryService = iHistoryService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createPatient(@RequestBody CreateHistoryRequest createHistoryRequest){
        BaseResponse baseResponse = iHistoryService.createHistory(createHistoryRequest);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<BaseResponse> getHistoryById(@PathVariable Long id){
        BaseResponse baseResponse = iHistoryService.getHistoryById(id);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<BaseResponse> DeleteHistoryById(@PathVariable Long id){
        BaseResponse baseResponse = iHistoryService.DeleteHistoryById(id);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }


  /*  @PatchMapping(path="/{id}")
    public ResponseEntity<BaseResponse> UpdateHistoryById(@PathVariable Long id, @RequestBody UpdatePatientRequest updatePatientRequest){
        BaseResponse baseResponse = iHistoryService.UpdateHistoryById(id,updatePatientRequest);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }*/

}
