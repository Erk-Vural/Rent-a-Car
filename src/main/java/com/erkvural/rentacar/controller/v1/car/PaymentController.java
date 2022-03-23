package com.erkvural.rentacar.controller.v1.car;

import com.erkvural.rentacar.core.exception.BusinessException;
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
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody PaymentCreateRequest createRequest, @RequestParam("rememberMe") boolean rememberMe) throws BusinessException {
        return this.paymentService.add(createRequest, rememberMe);
    }

    @GetMapping("/getAll")
    public DataResult<List<PaymentGetResponse>> getAll() {
        return paymentService.getAll();
    }

    @GetMapping("/get")
    public DataResult<PaymentGetResponse> get(@RequestParam("id") long id) throws BusinessException {
        return paymentService.getById(id);
    }
}
