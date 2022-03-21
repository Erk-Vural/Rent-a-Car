package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.core.utils.results.SuccessResult;
import com.erkvural.rentacar.dto.car.create.ColorCreateRequest;
import com.erkvural.rentacar.dto.car.get.ColorGetResponse;
import com.erkvural.rentacar.dto.car.update.ColorUpdateRequest;
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
    public Result add(ColorCreateRequest createRequest) throws BusinessException {
        checkColorNameExist(createRequest.getName());

        Color color = this.modelMapperService.forRequest().map(createRequest, Color.class);
        this.colorRepository.save(color);

        return new SuccessResult("Success, Color added: " + color.getName());
    }

    @Override
    public DataResult<List<ColorGetResponse>> getAll() {
        List<Color> result = colorRepository.findAll();
        List<ColorGetResponse> response = result.stream()
                .map(color -> modelMapperService.forDto()
                        .map(color, ColorGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>("Success, All Colors listed.", response);
    }

    @Override
    public DataResult<ColorGetResponse> getById(long id) throws BusinessException {
        checkColorIdExist(id);

        Color color = colorRepository.getById(id);
        ColorGetResponse response = modelMapperService.forDto().map(color, ColorGetResponse.class);

        return new SuccessDataResult<>("Success, Color with requested ID found.", response);
    }

    @Override
    public Result update(long id, ColorUpdateRequest updateRequest) throws BusinessException {
        checkColorIdExist(id);

        Color color = this.modelMapperService.forRequest().map(updateRequest, Color.class);
        color.setId(id);

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
