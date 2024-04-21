package com.proyectoMulti.MedicHealt.service.serviceImpl;

import com.proyectoMulti.MedicHealt.web.dtos.auth.response.BaseResponse;
import com.proyectoMulti.MedicHealt.web.dtos.biometricsData.request.CreateBiometricData;
import com.proyectoMulti.MedicHealt.web.dtos.history.request.CreateHistoryRequest;
import org.springframework.stereotype.Service;

@Service
public interface IBiometricsDataService {

    BaseResponse createBiometricsData(CreateBiometricData createBiometricData);

}
