package com.erkvural.rentacar.controller.v1.customer;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.customer.create.CorporateCustomerCreateDto;
import com.erkvural.rentacar.dto.customer.delete.CorporateCustomerDeleteDto;
import com.erkvural.rentacar.dto.customer.get.CorporateCustomerGetDto;
import com.erkvural.rentacar.dto.customer.update.CorporateCustomerUpdateDto;
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
    public Result add(@RequestBody CorporateCustomerCreateDto corporateCustomerCreateDto) {
        return this.corporateCustomerService.add(corporateCustomerCreateDto);
    }

    @GetMapping("/getAll")
    public DataResult<List<CorporateCustomerGetDto>> getAll() {
        return corporateCustomerService.getAll();
    }

    @GetMapping("/get")
    public DataResult<CorporateCustomerGetDto> get(@RequestParam("id") long id) {
        return corporateCustomerService.getById(id);
    }

    @PutMapping("/update")
    public Result update(@RequestParam("id") long id, @RequestBody CorporateCustomerUpdateDto corporateCustomerUpdateDto) {
        return this.corporateCustomerService.update(id, corporateCustomerUpdateDto);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") long id) {
        return this.corporateCustomerService.delete(id);
    }
}
