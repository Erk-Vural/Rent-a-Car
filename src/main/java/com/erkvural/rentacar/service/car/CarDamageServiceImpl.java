package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.constant.MessageStrings;
import com.erkvural.rentacar.constant.CarStatus;
import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.core.utils.results.SuccessResult;
import com.erkvural.rentacar.dto.car.create.CarDamageCreateRequest;
import com.erkvural.rentacar.dto.car.get.CarDamageGetResponse;
import com.erkvural.rentacar.dto.car.update.CarDamageUpdateRequest;
import com.erkvural.rentacar.entity.car.CarDamage;
import com.erkvural.rentacar.repository.car.CarDamageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CarDamageServiceImpl implements CarDamageService {
    private final CarDamageRepository repository;
    private final ModelMapperService modelMapperService;
    private final CarService carService;

    @Autowired
    public CarDamageServiceImpl(CarDamageRepository repository, CarService carService, ModelMapperService modelMapperService) {
        this.repository = repository;
        this.carService = carService;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CarDamageCreateRequest createRequest){
        checkCarIdExist(createRequest.getCarId());

        CarDamage carDamage = this.modelMapperService.forRequest().map(createRequest, CarDamage.class);

        carService.setCarStatus(CarStatus.DAMAGED, createRequest.getCarId());

        this.repository.save(carDamage);

        return new SuccessResult(MessageStrings.DAMAGE_ADDED);
    }

    @Override
    public DataResult<List<CarDamageGetResponse>> getAll() {
        List<CarDamage> result = repository.findAll();

        List<CarDamageGetResponse> response = result.stream()
                .map(carDamage -> modelMapperService.forResponse()
                        .map(carDamage, CarDamageGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.DAMAGES_LISTED, response);
    }

    @Override
    public DataResult<CarDamageGetResponse> getById(long id){
        checkCarDamageIdExist(id);

        CarDamage carDamage = repository.getById(id);
        CarDamageGetResponse response = modelMapperService.forResponse().map(carDamage, CarDamageGetResponse.class);

        return new SuccessDataResult<>(MessageStrings.DAMAGE_EXISTS, response);
    }

    @Override
    public Result update(long id, CarDamageUpdateRequest updateRequest){
        checkCarIdExist(updateRequest.getCarId());
        checkCarDamageIdExist(id);

        CarDamage carDamage = this.modelMapperService.forRequest().map(updateRequest, CarDamage.class);

        carDamage.setId(id);
        carService.setCarStatus(CarStatus.DAMAGED, updateRequest.getCarId());

        this.repository.save(carDamage);

        return new SuccessResult(MessageStrings.DAMAGE_UPDATED);
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkCarDamageIdExist(id);

        carService.setCarStatus(CarStatus.AVAILABLE, repository.findById(id).getCar().getId());

        this.repository.deleteById(id);

        return new SuccessResult(MessageStrings.DAMAGE_DELETED);
    }

    private void checkCarDamageIdExist(long id) throws BusinessException {
        if (Objects.nonNull(repository.findById(id)))
            throw new BusinessException(MessageStrings.DAMAGE_NOT_FOUND);
    }

    private void checkCarIdExist(long carId) throws BusinessException {
        if (Objects.nonNull(carService.getById(carId).getData()))
            throw new BusinessException(MessageStrings.CAR_NOT_FOUND);
    }
}
