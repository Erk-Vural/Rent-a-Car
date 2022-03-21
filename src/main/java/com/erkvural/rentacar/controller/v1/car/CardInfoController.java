package com.erkvural.rentacar.controller.v1.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.CardInfoCreateRequest;
import com.erkvural.rentacar.dto.car.get.CardInfoGetResponse;
import com.erkvural.rentacar.dto.car.update.CardInfoUpdateRequest;
import com.erkvural.rentacar.service.car.CardInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardInfoController {

    private final CardInfoService cardInfoService;

    @Autowired
    public CardInfoController(CardInfoService cardInfoService) {
        this.cardInfoService = cardInfoService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CardInfoCreateRequest createRequest) throws BusinessException {
        return this.cardInfoService.add(createRequest);
    }

    @GetMapping("/getAll")
    public DataResult<List<CardInfoGetResponse>> getAll() {
        return cardInfoService.getAll();
    }

    @GetMapping("/get")
    public DataResult<CardInfoGetResponse> get(@RequestParam("id") long id) throws BusinessException {
        return cardInfoService.getById(id);
    }

    @PutMapping("/update")
    public Result update(@RequestParam("id") long id, @RequestBody CardInfoUpdateRequest updateRequest) throws BusinessException {
        return this.cardInfoService.update(id, updateRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") long id) throws BusinessException {
        return this.cardInfoService.delete(id);
    }
}
