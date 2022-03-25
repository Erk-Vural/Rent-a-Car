package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.constant.MessageStrings;
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

    private final ColorRepository repository;
    private final ModelMapperService modelMapperService;

    @Autowired
    public ColorServiceImpl(ColorRepository repository, ModelMapperService modelMapperService) {
        this.repository = repository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(ColorCreateRequest createRequest){
        checkColorNameExist(createRequest.getName());

        Color color = this.modelMapperService.forRequest().map(createRequest, Color.class);
        this.repository.save(color);

        return new SuccessResult(MessageStrings.COLOR_ADDED);
    }

    @Override
    public DataResult<List<ColorGetResponse>> getAll() {
        List<Color> result = repository.findAll();
        List<ColorGetResponse> response = result.stream()
                .map(color -> modelMapperService.forResponse()
                        .map(color, ColorGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.COLORS_LISTED, response);
    }

    @Override
    public DataResult<ColorGetResponse> getById(long id){
        checkColorIdExist(id);

        Color color = repository.getById(id);
        ColorGetResponse response = modelMapperService.forResponse().map(color, ColorGetResponse.class);

        return new SuccessDataResult<>(MessageStrings.COLOR_FOUND, response);
    }

    @Override
    public Result update(long id, ColorUpdateRequest updateRequest){
        checkColorIdExist(id);
        checkColorNameExist(updateRequest.getName());

        Color color = this.modelMapperService.forRequest().map(updateRequest, Color.class);
        color.setId(id);

        this.repository.save(color);

        return new SuccessResult(MessageStrings.COLOR_UPDATED);
    }

    @Override
    public Result delete(long id){
        checkColorIdExist(id);

        this.repository.deleteById(id);

        return new SuccessResult(MessageStrings.COLOR_DELETED);
    }

    private void checkColorIdExist(long id) throws BusinessException {
        if (Objects.nonNull(repository.findById(id)))
            throw new BusinessException(MessageStrings.COLOR_NOT_FOUND);
    }

    private void checkColorNameExist(String name) throws BusinessException {
        if (Objects.nonNull(repository.findByName(name)))
            throw new BusinessException(MessageStrings.COLOR_EXISTS);
    }
}
