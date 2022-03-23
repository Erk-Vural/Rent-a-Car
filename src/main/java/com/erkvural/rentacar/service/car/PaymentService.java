package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.PaymentCreateRequest;
import com.erkvural.rentacar.dto.car.get.PaymentGetResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {

    Result add(PaymentCreateRequest createRequest, boolean rememberCardInfo) throws BusinessException;

    DataResult<List<PaymentGetResponse>> getAll();

    DataResult<PaymentGetResponse> getById(long id) throws BusinessException;

}
