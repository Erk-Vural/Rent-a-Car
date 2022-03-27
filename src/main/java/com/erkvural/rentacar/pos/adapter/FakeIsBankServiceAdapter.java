package com.erkvural.rentacar.pos.adapter;

import com.erkvural.rentacar.constant.MessageStrings;
import com.erkvural.rentacar.core.utils.results.ErrorResult;
import com.erkvural.rentacar.dto.car.create.PaymentCreateRequest;
import com.erkvural.rentacar.pos.service.PosService;
import com.erkvural.rentacar.pos.service.PosServiceIsBankImpl;
import org.springframework.stereotype.Service;

@Service

public class FakeIsBankServiceAdapter implements PosService {
    @Override
    public void addPayment(PaymentCreateRequest createRequest) {
        PosServiceIsBankImpl posServiceIsBank = new PosServiceIsBankImpl();

        try {
            posServiceIsBank.makePayment(createRequest.getCardInfo());
        } catch (Exception e) {
            new ErrorResult(MessageStrings.PAYMENT_CARD_FAIL);
        }
    }
}
