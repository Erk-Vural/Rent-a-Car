package com.erkvural.rentacar.pos.service;

import com.erkvural.rentacar.constant.MessageStrings;
import com.erkvural.rentacar.core.utils.results.SuccessResult;
import com.erkvural.rentacar.dto.car.create.CardInfoCreateRequest;

public class PosServiceIsBankImpl {
    public void makePayment(CardInfoCreateRequest createRequest) {

        new SuccessResult(MessageStrings.POS_IS_BANK_SUCCESSFUL);
    }
}
