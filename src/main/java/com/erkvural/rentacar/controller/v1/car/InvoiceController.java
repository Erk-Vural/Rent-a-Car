package com.erkvural.rentacar.controller.v1.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.dto.car.get.InvoiceGetResponse;
import com.erkvural.rentacar.service.car.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/car/invoices")
public class InvoiceController {

    private final InvoiceService service;

    @Autowired
    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @GetMapping("/get/all")
    public DataResult<List<InvoiceGetResponse>> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/id={id}")
    public DataResult<InvoiceGetResponse> get(@PathVariable long id) throws BusinessException {
        return service.getById(id);
    }

    @GetMapping("/get/customerId={customerId}")
    public DataResult<List<InvoiceGetResponse>> getByCustomerId(@PathVariable long customerId) throws BusinessException {
        return service.getByCustomerId(customerId);
    }

    @GetMapping("/get/start-date={startDate}+end-date={endDate}")
    public DataResult<List<InvoiceGetResponse>> getByBetweenDates(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
        return service.getByBetweenDates(endDate, startDate);
    }
}
