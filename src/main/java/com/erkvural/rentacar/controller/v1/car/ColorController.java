package com.erkvural.rentacar.controller.v1.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.dto.car.create.ColorCreateDto;
import com.erkvural.rentacar.dto.car.get.ColorGetDto;
import com.erkvural.rentacar.dto.car.update.ColorUpdateDto;
import com.erkvural.rentacar.service.car.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car/colors")
public class ColorController {
    private final ColorService colorService;

    @Autowired
    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody ColorCreateDto colorCreateDto) throws BusinessException {
        return this.colorService.add(colorCreateDto);
    }

    @GetMapping("/getAll")
    public DataResult<List<ColorGetDto>> getAll() {
        return colorService.getAll();
    }

    @GetMapping("/get")
    public DataResult<ColorGetDto> get(@RequestParam("id") long id) throws BusinessException {
        return colorService.getById(id);
    }

    @PutMapping("/update")
    public Result update(@RequestParam("id") long id, @RequestBody ColorUpdateDto colorUpdateDto) throws BusinessException {
        return this.colorService.update(id, colorUpdateDto);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") long id) throws BusinessException {
        return this.colorService.delete(id);
    }
}
