package com.erkvural.rentacar.service.customer;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.customer.create.CorporateCustomerCreateDto;
import com.erkvural.rentacar.dto.customer.get.CorporateCustomerGetDto;
import com.erkvural.rentacar.dto.customer.update.CorporateCustomerUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorporateCustomerServiceImpl implements CorporateCustomerService{
    @Override
    public Result add(CorporateCustomerCreateDto corporateCustomerCreateDto) {
        return null;
    }

    @Override
    public DataResult<List<CorporateCustomerGetDto>> getAll() {
        return null;
    }

    @Override
    public DataResult<CorporateCustomerGetDto> getById(long id) {
        return null;
    }

    @Override
    public Result update(long id, CorporateCustomerUpdateDto corporateCustomerUpdateDto) {
        return null;
    }

    @Override
    public Result delete(long id) {
        return null;
    }
}
