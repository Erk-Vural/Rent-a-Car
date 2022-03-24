package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CardInfoCreateRequest;
import com.erkvural.rentacar.dto.car.get.CardInfoGetResponse;
import com.erkvural.rentacar.dto.car.update.CardInfoUpdateRequest;
import com.erkvural.rentacar.entity.car.CardInfo;

import java.util.List;


public interface CardInfoService {
    Result add(CardInfoCreateRequest createRequest);

    DataResult<CardInfo> addByPayment(CardInfoCreateRequest createRequest);

    DataResult<List<CardInfoGetResponse>> getAll();

    DataResult<CardInfoGetResponse> getById(long id);

    Result update(long id, CardInfoUpdateRequest updateRequest);

    Result delete(long id);
}
