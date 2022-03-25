package com.erkvural.rentacar.service.customer;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.customer.create.IndividualCustomerCreateRequest;
import com.erkvural.rentacar.dto.customer.get.IndividualCustomerGetResponse;
import com.erkvural.rentacar.dto.customer.update.IndividualCustomerUpdateRequest;

import java.util.List;

public interface IndividualCustomerService {

    Result add(IndividualCustomerCreateRequest createRequest);

    DataResult<List<IndividualCustomerGetResponse>> getAll();

    DataResult<IndividualCustomerGetResponse> getById(long id);

    Result update(long id, IndividualCustomerUpdateRequest updateRequest);

    Result delete(long id);
}
