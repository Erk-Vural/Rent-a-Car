package com.erkvural.rentacar.service.customer;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.customer.create.CorporateCustomerCreateDto;
import com.erkvural.rentacar.dto.customer.get.CorporateCustomerGetDto;
import com.erkvural.rentacar.dto.customer.update.CorporateCustomerUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CorporateCustomerService {
    Result add(CorporateCustomerCreateDto corporateCustomerCreateDto);

    DataResult<List<CorporateCustomerGetDto>> getAll();

    DataResult<CorporateCustomerGetDto> getById(long id) throws BusinessException;

    Result update(long id, CorporateCustomerUpdateDto corporateCustomerUpdateDto) throws BusinessException;

    Result delete(long id) throws BusinessException;
}
