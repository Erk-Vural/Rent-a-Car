package com.erkvural.rentacar.controller.v1.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.dto.car.get.InvoiceGetResponse;
import com.erkvural.rentacar.service.car.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Cacheable("invoices")
    @GetMapping("/get/all")
    public DataResult<List<InvoiceGetResponse>> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/id={id}")
    public DataResult<InvoiceGetResponse> get(@PathVariable long id) {
        return service.getById(id);
    }

    @GetMapping("/get/customerId={customerId}")
    public DataResult<List<InvoiceGetResponse>> getByCustomerId(@PathVariable long customerId) {
        return service.getByCustomerId(customerId);
    }

    @GetMapping("/get/start-date={startDate}+end-date={endDate}")
    public DataResult<List<InvoiceGetResponse>> getByBetweenDates(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                  @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return service.getByBetweenDates(endDate, startDate);
    }
}
