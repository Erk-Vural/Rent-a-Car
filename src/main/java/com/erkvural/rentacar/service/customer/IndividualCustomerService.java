package com.erkvural.rentacar.service.customer;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.customer.create.IndividualCustomerCreateDto;
import com.erkvural.rentacar.dto.customer.delete.IndividualCustomerDeleteDto;
import com.erkvural.rentacar.dto.customer.get.IndividualCustomerGetDto;
import com.erkvural.rentacar.dto.customer.update.IndividualCustomerUpdateDto;

import java.util.List;

public interface IndividualCustomerService {

    Result add(IndividualCustomerCreateDto individualCustomerCreateDto);

    DataResult<List<IndividualCustomerGetDto>> getAll();

    DataResult<IndividualCustomerGetDto> getById(long id);

    Result update(IndividualCustomerUpdateDto individualCustomerUpdateDto);

    Result delete(IndividualCustomerDeleteDto individualCustomerDeleteDto);
}
