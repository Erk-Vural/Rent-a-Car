package com.erkvural.rentacar.service.customer;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.customer.create.IndividualCustomerCreateRequest;
import com.erkvural.rentacar.dto.customer.get.IndividualCustomerGetResponse;
import com.erkvural.rentacar.dto.customer.update.IndividualCustomerUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IndividualCustomerService {

    Result add(IndividualCustomerCreateRequest createRequest) throws BusinessException;

    DataResult<List<IndividualCustomerGetResponse>> getAll();

    DataResult<IndividualCustomerGetResponse> getById(long id) throws BusinessException;

    Result update(long id, IndividualCustomerUpdateRequest updateRequest) throws BusinessException;

    Result delete(long id) throws BusinessException;
}
