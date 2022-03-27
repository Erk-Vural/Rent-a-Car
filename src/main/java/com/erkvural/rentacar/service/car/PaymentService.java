package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.PaymentCreateRequest;
import com.erkvural.rentacar.dto.car.get.PaymentGetResponse;

import java.util.List;

public interface PaymentService {

    Result add(PaymentCreateRequest createRequest, boolean rememberCardInfo);

    void addForExtra(PaymentCreateRequest createRequest, boolean rememberCardInfo, double newExtraTotal);

    DataResult<List<PaymentGetResponse>> getAll();

    DataResult<PaymentGetResponse> getById(long id);

}
