package com.erkvural.rentacar.service.customer;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.customer.create.IndividualCustomerCreateDto;
import com.erkvural.rentacar.dto.customer.get.IndividualCustomerGetDto;
import com.erkvural.rentacar.dto.customer.update.IndividualCustomerUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IndividualCustomerService {

    Result add(IndividualCustomerCreateDto individualCustomerCreateDto);

    DataResult<List<IndividualCustomerGetDto>> getAll();

    DataResult<IndividualCustomerGetDto> getById(long id);

    Result update(long id, IndividualCustomerUpdateDto individualCustomerUpdateDto);

    Result delete(long id);
}
