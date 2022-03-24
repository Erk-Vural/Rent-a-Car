package com.erkvural.rentacar.pos.adapter;

import com.erkvural.rentacar.pos.service.PosService;
import com.erkvural.rentacar.pos.service.PosServiceHalkBankImpl;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.PaymentCreateRequest;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class FakeHalkBankServiceAdapter implements PosService {
    @Override
    public Result addPayment(PaymentCreateRequest createRequest) {
        PosServiceHalkBankImpl posServiceHalkBank = new PosServiceHalkBankImpl();

        return posServiceHalkBank.makePayment(createRequest.getCardInfo());
    }
}
