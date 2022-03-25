package com.erkvural.rentacar.service.customer;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.customer.create.CorporateCustomerCreateRequest;
import com.erkvural.rentacar.dto.customer.get.CorporateCustomerGetResponse;
import com.erkvural.rentacar.dto.customer.update.CorporateCustomerUpdateRequest;

import java.util.List;

public interface CorporateCustomerService {

    Result add(CorporateCustomerCreateRequest createRequest);

    DataResult<List<CorporateCustomerGetResponse>> getAll();

    DataResult<CorporateCustomerGetResponse> getById(long id);

    Result update(long id, CorporateCustomerUpdateRequest updateRequest);

    Result delete(long id);
}
