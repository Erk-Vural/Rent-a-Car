package com.erkvural.rentacar.controller.v1.customer;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.customer.create.IndividualCustomerCreateRequest;
import com.erkvural.rentacar.dto.customer.get.IndividualCustomerGetResponse;
import com.erkvural.rentacar.dto.customer.update.IndividualCustomerUpdateRequest;
import com.erkvural.rentacar.service.customer.IndividualCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer/individual")
public class IndividualCustomerController {
    private final IndividualCustomerService individualCustomerService;

    @Autowired
    public IndividualCustomerController(IndividualCustomerService individualCustomerService) {
        this.individualCustomerService = individualCustomerService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody IndividualCustomerCreateRequest individualCustomerCreateDto) throws BusinessException {
        return this.individualCustomerService.add(individualCustomerCreateDto);
    }

    @GetMapping("/getAll")
    public DataResult<List<IndividualCustomerGetResponse>> getAll() {
        return individualCustomerService.getAll();
    }

    @GetMapping("/get")
    public DataResult<IndividualCustomerGetResponse> get(@RequestParam("id") long id) throws BusinessException {
        return individualCustomerService.getById(id);
    }

    @PutMapping("/update")
    public Result update(@RequestParam("id") long id, @RequestBody IndividualCustomerUpdateRequest individualCustomerUpdateDto) throws BusinessException {
        return this.individualCustomerService.update(id, individualCustomerUpdateDto);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") long id) throws BusinessException {
        return this.individualCustomerService.delete(id);
    }
}
