package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.InvoiceCreateRequest;
import com.erkvural.rentacar.dto.car.get.InvoiceGetResponse;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceService {

    Result add(InvoiceCreateRequest createRequest);

    DataResult<List<InvoiceGetResponse>> getAll();

    DataResult<InvoiceGetResponse> getById(long id);

    DataResult<List<InvoiceGetResponse>> getByCustomerId(long customerId);

    DataResult<List<InvoiceGetResponse>> getByBetweenDates(LocalDate startDate, LocalDate endDate);
}
