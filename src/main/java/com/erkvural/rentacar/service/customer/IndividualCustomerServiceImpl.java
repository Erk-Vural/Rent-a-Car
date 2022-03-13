package com.erkvural.rentacar.service.customer;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.customer.create.IndividualCustomerCreateDto;
import com.erkvural.rentacar.dto.customer.get.IndividualCustomerGetDto;
import com.erkvural.rentacar.dto.customer.update.IndividualCustomerUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndividualCustomerServiceImpl implements IndividualCustomerService{
    @Override
    public Result add(IndividualCustomerCreateDto individualCustomerCreateDto) {
        return null;
    }

    @Override
    public DataResult<List<IndividualCustomerGetDto>> getAll() {
        return null;
    }

    @Override
    public DataResult<IndividualCustomerGetDto> getById(long id) {
        return null;
    }

    @Override
    public Result update(long id, IndividualCustomerUpdateDto individualCustomerUpdateDto) {
        return null;
    }

    @Override
    public Result delete(long id) {
        return null;
    }
}
