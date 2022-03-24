package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.constant.MessageStrings;
import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.core.utils.results.SuccessResult;
import com.erkvural.rentacar.dto.car.create.CityCreateRequest;
import com.erkvural.rentacar.dto.car.get.CityGetResponse;
import com.erkvural.rentacar.dto.car.update.CityUpdateRequest;
import com.erkvural.rentacar.entity.car.City;
import com.erkvural.rentacar.repository.car.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository repository;
    private final ModelMapperService modelMapperService;

    @Autowired
    public CityServiceImpl(CityRepository repository, ModelMapperService modelMapperService) {
        this.repository = repository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CityCreateRequest createRequest) throws BusinessException {
        checkCityNameExist(createRequest.getName());

        City city = this.modelMapperService.forRequest().map(createRequest, City.class);
        this.repository.save(city);

        return new SuccessResult(MessageStrings.CITY_ADDED);
    }

    @Override
    public DataResult<List<CityGetResponse>> getAll() {
        List<City> result = repository.findAll();

        List<CityGetResponse> response = result.stream()
                .map(city -> modelMapperService.forResponse()
                        .map(city, CityGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.CITIES_LISTED, response);
    }

    @Override
    public DataResult<CityGetResponse> getById(long id) throws BusinessException {
        checkCityIdExist(id);

        City city = repository.getById(id);
        CityGetResponse response = modelMapperService.forResponse().map(city, CityGetResponse.class);

        return new SuccessDataResult<>(MessageStrings.CITYBYID, response);
    }

    @Override
    public Result update(long id, CityUpdateRequest updateRequest) throws BusinessException {
        checkCityIdExist(id);

        City city = this.modelMapperService.forRequest().map(updateRequest, City.class);
        city.setId(id);

        this.repository.save(city);

        return new SuccessResult(MessageStrings.CITY_UPDATED);
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkCityIdExist(id);

        this.repository.deleteById(id);

        return new SuccessResult(MessageStrings.CITY_DELETED);
    }

    private void checkCityIdExist(long id) throws BusinessException {
        if (Objects.nonNull(repository.findById(id)))
            throw new BusinessException(MessageStrings.CITY_NOT_FOUND);
    }

    private void checkCityNameExist(String name) throws BusinessException {
        if (!Objects.nonNull(repository.findByName(name)))
            throw new BusinessException(MessageStrings.CITY_NAME_NOT_FOUND);

    }
}
