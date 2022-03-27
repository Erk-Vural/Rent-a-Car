package com.erkvural.rentacar.pos.adapter;

import com.erkvural.rentacar.constant.MessageStrings;
import com.erkvural.rentacar.core.utils.results.ErrorResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.PaymentCreateRequest;
import com.erkvural.rentacar.pos.service.PosService;
import com.erkvural.rentacar.pos.service.PosServiceHalkBankImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class FakeHalkBankServiceAdapter implements PosService {
    @Override
    public Result addPayment(PaymentCreateRequest createRequest) {
        PosServiceHalkBankImpl posServiceHalkBank = new PosServiceHalkBankImpl();

        try {
            return posServiceHalkBank.makePayment(createRequest.getCardInfo());
        } catch (Exception e) {
            return new ErrorResult(MessageStrings.PAYMENT_CARD_FAIL);
        }
    }
}
