package com.erkvural.rentacar.controller.v1.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.dto.car.get.InvoiceGetResponse;
import com.erkvural.rentacar.service.car.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/getAll")
    public DataResult<List<InvoiceGetResponse>> getAll() {
        return invoiceService.getAll();
    }

    @GetMapping("/get")
    public DataResult<InvoiceGetResponse> get(@RequestParam("id") long id) throws BusinessException {
        return invoiceService.getById(id);
    }

    @GetMapping("/getByCustomerId")
    public DataResult<InvoiceGetResponse> getByCustomerId(@RequestParam("id") long id) throws BusinessException {
        return invoiceService.getByCustomerId(id);
    }

    public DataResult<InvoiceGetResponse> getByBetweenDates(@RequestParam("endDate") LocalDate endDate,
                                                            @RequestParam("startDate") LocalDate startDate) {
        return invoiceService.getByBetweenDates(endDate, startDate);
    }
}
