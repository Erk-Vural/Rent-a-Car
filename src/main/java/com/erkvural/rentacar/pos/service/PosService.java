package com.erkvural.rentacar.pos.service;

import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.PaymentCreateRequest;
import org.springframework.stereotype.Service;

@Service
public interface PosService {

    Result addPayment(PaymentCreateRequest createRequest);
}
