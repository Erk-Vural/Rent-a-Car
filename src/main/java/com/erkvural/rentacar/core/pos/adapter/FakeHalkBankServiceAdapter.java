package com.erkvural.rentacar.core.pos.adapter;

import com.erkvural.rentacar.core.pos.service.PosService;
import com.erkvural.rentacar.core.pos.service.PosServiceHalkBankImpl;
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
