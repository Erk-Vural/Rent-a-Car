package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.BrandCreateRequest;
import com.erkvural.rentacar.dto.car.get.BrandGetResponse;
import com.erkvural.rentacar.dto.car.update.BrandUpdateRequest;

import java.util.List;


public interface BrandService {

    Result add(BrandCreateRequest createRequest);

    DataResult<List<BrandGetResponse>> getAll();

    DataResult<BrandGetResponse> getById(long id);

    Result update(long id, BrandUpdateRequest updateRequest);

    Result delete(long id);
}
