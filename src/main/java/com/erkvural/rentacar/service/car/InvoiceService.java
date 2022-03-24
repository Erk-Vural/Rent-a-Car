package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.InvoiceCreateRequest;
import com.erkvural.rentacar.dto.car.get.InvoiceGetResponse;
import com.erkvural.rentacar.dto.car.update.BrandUpdateRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceService {

    Result add(InvoiceCreateRequest createRequest) throws BusinessException;

    DataResult<List<InvoiceGetResponse>> getAll();

    DataResult<InvoiceGetResponse> getById(long id) throws BusinessException;

    DataResult<List<InvoiceGetResponse>> getByCustomerId(long customerId) throws BusinessException;

    DataResult<List<InvoiceGetResponse>> getByBetweenDates(LocalDate startDate, LocalDate endDate);

    Result update(long id, BrandUpdateRequest updateRequest) throws BusinessException;

    Result delete(long id) throws BusinessException;
}
