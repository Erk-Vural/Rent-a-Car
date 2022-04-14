package com.erkvural.rentacar.controller.v1.car;

import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CardInfoCreateRequest;
import com.erkvural.rentacar.dto.car.get.CardInfoGetResponse;
import com.erkvural.rentacar.dto.car.update.CardInfoUpdateRequest;
import com.erkvural.rentacar.service.car.CardInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/car/card-infos")
public class CardInfoController {

    private final CardInfoService service;

    @Autowired
    public CardInfoController(CardInfoService cardInfoService) {
        this.service = cardInfoService;
    }

    @PostMapping("/add/customerId={customerId}")
    public Result add(@RequestBody  @Valid CardInfoCreateRequest createRequest, @PathVariable long customerId) {
        return this.service.add(createRequest, customerId);
    }

    @GetMapping("/get/all")
    public DataResult<List<CardInfoGetResponse>> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/id={id}")
    public DataResult<CardInfoGetResponse> get(@PathVariable long id) {
        return service.getById(id);
    }

    @PutMapping("/update/id={id}")
    public Result update(@PathVariable long id, @RequestBody  @Valid  CardInfoUpdateRequest updateRequest) {
        return this.service.update(id, updateRequest);
    }

    @DeleteMapping("/delete/id={id}")
    public Result delete(@PathVariable long id) {
        return this.service.delete(id);
    }
}
