package com.erkvural.rentacar.pos.adapter;

import com.erkvural.rentacar.pos.service.PosService;
import com.erkvural.rentacar.pos.service.PosServiceIsBankImpl;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.PaymentCreateRequest;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service

public class FakeIsBankServiceAdapter implements PosService {
    @Override
    public Result addPayment(PaymentCreateRequest createRequest) {
        PosServiceIsBankImpl posServiceIsBank = new PosServiceIsBankImpl();

        return posServiceIsBank.makePayment(createRequest.getCardInfo());
    }
}
