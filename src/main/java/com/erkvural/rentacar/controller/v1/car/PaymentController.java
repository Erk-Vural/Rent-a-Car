package com.erkvural.rentacar.controller.v1.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.PaymentCreateRequest;
import com.erkvural.rentacar.dto.car.get.PaymentGetResponse;
import com.erkvural.rentacar.service.car.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car/payments")
public class PaymentController {
    private final PaymentService service;

    @Autowired
    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Result add(@RequestBody PaymentCreateRequest createRequest, @RequestParam("rememberMe") boolean rememberMe) {
        return this.service.add(createRequest, rememberMe);
    }

    @GetMapping("/get/all")
    public DataResult<List<PaymentGetResponse>> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/id={id}")
    public DataResult<PaymentGetResponse> get(@PathVariable long id) {
        return service.getById(id);
    }
}
