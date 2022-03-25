package com.erkvural.rentacar.controller.v1.customer;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.customer.create.CorporateCustomerCreateRequest;
import com.erkvural.rentacar.dto.customer.get.CorporateCustomerGetResponse;
import com.erkvural.rentacar.dto.customer.update.CorporateCustomerUpdateRequest;
import com.erkvural.rentacar.service.customer.CorporateCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer/corporates")
public class CorporateCustomersController {
    private final CorporateCustomerService service;

    @Autowired
    public CorporateCustomersController(CorporateCustomerService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CorporateCustomerCreateRequest createRequest) {
        return this.service.add(createRequest);
    }

    @GetMapping("/get/all")
    public DataResult<List<CorporateCustomerGetResponse>> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/id={id}")
    public DataResult<CorporateCustomerGetResponse> get(@PathVariable long id) {
        return service.getById(id);
    }

    @PutMapping("/update/{id}")
    public Result update(@PathVariable long id, @RequestBody CorporateCustomerUpdateRequest updateRequest) {
        return this.service.update(id, updateRequest);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable long id) {
        return this.service.delete(id);
    }
}
