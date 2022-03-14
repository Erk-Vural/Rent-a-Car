package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.core.utils.results.SuccessResult;
import com.erkvural.rentacar.dto.car.create.ColorCreateDto;
import com.erkvural.rentacar.dto.car.get.ColorGetDto;
import com.erkvural.rentacar.dto.car.update.ColorUpdateDto;
import com.erkvural.rentacar.entity.car.Color;
import com.erkvural.rentacar.repository.car.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;
    private final ModelMapperService modelMapperService;

    @Autowired
    public ColorServiceImpl(ColorRepository colorRepository, ModelMapperService modelMapperService) {
        this.colorRepository = colorRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(ColorCreateDto colorCreateDto) throws BusinessException {
        checkColorNameExist(colorCreateDto.getName());

        Color color = this.modelMapperService.forRequest().map(colorCreateDto, Color.class);
        this.colorRepository.save(color);

        return new SuccessResult("Success, Color added: " + color.getName());
    }

    @Override
    public DataResult<List<ColorGetDto>> getAll() {
        List<Color> result = colorRepository.findAll();
        List<ColorGetDto> response = result.stream()
                .map(color -> modelMapperService.forDto()
                        .map(color, ColorGetDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>("Success, All Colors listed.", response);
    }

    @Override
    public DataResult<ColorGetDto> getById(long id) throws BusinessException {
        checkColorIdExist(id);

        Color color = colorRepository.getById(id);
        ColorGetDto response = modelMapperService.forDto().map(color, ColorGetDto.class);

        return new SuccessDataResult<>("Success, Color with requested ID found.", response);
    }

    @Override
    public Result update(long id, ColorUpdateDto colorUpdateDto) throws BusinessException {
        checkColorIdExist(id);

        Color color = this.modelMapperService.forRequest().map(colorUpdateDto, Color.class);
        this.colorRepository.save(color);

        return new SuccessResult("Success, Color updated: " + color.getName());
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkColorIdExist(id);

        this.colorRepository.deleteById(id);

        return new SuccessResult("Success, Color deleted with requested ID: " + id);
    }

    private void checkColorIdExist(long id) throws BusinessException {
        if (Objects.nonNull(colorRepository.findById(id)))
            throw new BusinessException("Can't find Color with id: " + id);
    }

    private void checkColorNameExist(String name) throws BusinessException {
        if (!Objects.nonNull(colorRepository.findByName(name)))
            throw new BusinessException("Color with same name exists: " + name);

    }
}
