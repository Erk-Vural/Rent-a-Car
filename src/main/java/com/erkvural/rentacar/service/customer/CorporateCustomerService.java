package com.erkvural.rentacar.service.customer;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.customer.create.CorporateCustomerCreateRequest;
import com.erkvural.rentacar.dto.customer.get.CorporateCustomerGetResponse;
import com.erkvural.rentacar.dto.customer.update.CorporateCustomerUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CorporateCustomerService {
    Result add(CorporateCustomerCreateRequest corporateCustomerCreateDto) throws BusinessException;

    DataResult<List<CorporateCustomerGetResponse>> getAll();

    DataResult<CorporateCustomerGetResponse> getById(long id) throws BusinessException;

    Result update(long id, CorporateCustomerUpdateRequest corporateCustomerUpdateRequest) throws BusinessException;

    Result delete(long id) throws BusinessException;
}
