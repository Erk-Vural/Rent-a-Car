package com.erkvural.rentacar.pos.service;

import com.erkvural.rentacar.constant.MessageStrings;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessResult;
import com.erkvural.rentacar.dto.car.create.CardInfoCreateRequest;

public class PosServiceHalkBankImpl {
    public Result makePayment(CardInfoCreateRequest createRequest) {

        return new SuccessResult(MessageStrings.POS_HALK_BANK_SUCCESSFUL);
    }
}
