package com.erkvural.rentacar.controller.v1.customer;

import com.erkvural.rentacar.core.exception.BusinessException;
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
@RequestMapping("/api/v1/customer/corporate")
public class CorporateCustomersController {
    private final CorporateCustomerService corporateCustomerService;

    @Autowired
    public CorporateCustomersController(CorporateCustomerService corporateCustomerService) {
        this.corporateCustomerService = corporateCustomerService;
    }


    @PostMapping("/add")
    public Result add(@RequestBody CorporateCustomerCreateRequest corporateCustomerCreateDto) throws BusinessException {
        return this.corporateCustomerService.add(corporateCustomerCreateDto);
    }

    @GetMapping("/getAll")
    public DataResult<List<CorporateCustomerGetResponse>> getAll() {
        return corporateCustomerService.getAll();
    }

    @GetMapping("/get")
    public DataResult<CorporateCustomerGetResponse> get(@RequestParam("id") long id) throws BusinessException {
        return corporateCustomerService.getById(id);
    }

    @PutMapping("/update")
    public Result update(@RequestParam("id") long id, @RequestBody CorporateCustomerUpdateRequest corporateCustomerUpdateRequest) throws BusinessException {
        return this.corporateCustomerService.update(id, corporateCustomerUpdateRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") long id) throws BusinessException {
        return this.corporateCustomerService.delete(id);
    }
}
