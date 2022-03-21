package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CardInfoCreateRequest;
import com.erkvural.rentacar.dto.car.get.CardInfoGetResponse;
import com.erkvural.rentacar.dto.car.update.CardInfoUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardInfoService {
    Result add(CardInfoCreateRequest createRequest) throws BusinessException;

    DataResult<List<CardInfoGetResponse>> getAll();

    DataResult<CardInfoGetResponse> getById(long id) throws BusinessException;

    Result update(long id, CardInfoUpdateRequest updateRequest) throws BusinessException;

    Result delete(long id) throws BusinessException;
}
