package com.erkvural.rentacar.service.customer;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.customer.create.CorporateCustomerCreateDto;
import com.erkvural.rentacar.dto.customer.delete.CorporateCustomerDeleteDto;
import com.erkvural.rentacar.dto.customer.get.CorporateCustomerGetDto;
import com.erkvural.rentacar.dto.customer.update.CorporateCustomerUpdateDto;

import java.util.List;

public interface CorporateCustomerService {
    Result add(CorporateCustomerCreateDto corporateCustomerCreateDto);

    DataResult<List<CorporateCustomerGetDto>> getAll();

    DataResult<CorporateCustomerGetDto> getById(long id);

    Result update(CorporateCustomerUpdateDto corporateCustomerUpdateDto);

    Result delete(CorporateCustomerDeleteDto corporateCustomerDeleteDto);
}
