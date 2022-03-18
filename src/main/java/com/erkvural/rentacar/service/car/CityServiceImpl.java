package com.erkvural.rentacar.service.car;

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

    private final CityRepository cityRepository;
    private final ModelMapperService modelMapperService;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, ModelMapperService modelMapperService) {
        this.cityRepository = cityRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CityCreateRequest cityCreateDto) throws BusinessException {
        checkCityNameExist(cityCreateDto.getName());

        City city = this.modelMapperService.forRequest().map(cityCreateDto, City.class);
        this.cityRepository.save(city);

        return new SuccessResult("Success, City added: " + city.getName());
    }

    @Override
    public DataResult<List<CityGetResponse>> getAll() {
        List<City> result = cityRepository.findAll();

        List<CityGetResponse> response = result.stream()
                .map(city -> modelMapperService.forDto()
                        .map(city, CityGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>("Success, All Cities listed.", response);
    }

    @Override
    public DataResult<CityGetResponse> getById(long id) throws BusinessException {
        checkCityIdExist(id);

        City city = cityRepository.getById(id);
        CityGetResponse response = modelMapperService.forDto().map(city, CityGetResponse.class);

        return new SuccessDataResult<>("Success, City with requested ID found.", response);
    }

    @Override
    public Result update(long id, CityUpdateRequest cityUpdateDto) throws BusinessException {
        checkCityIdExist(id);

        City city = this.modelMapperService.forRequest().map(cityUpdateDto, City.class);
        this.cityRepository.save(city);

        return new SuccessResult("Success, City updated: " + city.getName());
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkCityIdExist(id);

        this.cityRepository.deleteById(id);

        return new SuccessResult("Success, City deleted with requested ID: " + id);
    }

    private void checkCityIdExist(long id) throws BusinessException {
        if (Objects.nonNull(cityRepository.findById(id)))
            throw new BusinessException("Can't find City with id: " + id);
    }

    private void checkCityNameExist(String name) throws BusinessException {
        if (!Objects.nonNull(cityRepository.findByName(name)))
            throw new BusinessException("City with same name exists: " + name);

    }
}
