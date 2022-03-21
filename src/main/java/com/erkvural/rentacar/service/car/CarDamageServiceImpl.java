package com.erkvural.rentacar.service.car;

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
import com.erkvural.rentacar.repository.car.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CarDamageServiceImpl implements CarDamageService {
    private final CarDamageRepository carDamageRepository;
    private final CarRepository carRepository;
    private final ModelMapperService modelMapperService;

    @Autowired
    public CarDamageServiceImpl(CarDamageRepository carDamageRepository, CarRepository carRepository, ModelMapperService modelMapperService) {
        this.carDamageRepository = carDamageRepository;
        this.carRepository = carRepository;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public Result add(CarDamageCreateRequest createRequest) throws BusinessException {
        checkCarIdExist(createRequest.getCarId());

        CarDamage carDamage = this.modelMapperService.forRequest().map(createRequest, CarDamage.class);
        this.carDamageRepository.save(carDamage);

        return new SuccessResult("Success, Car Damage added: " + carDamage);
    }

    @Override
    public DataResult<List<CarDamageGetResponse>> getAll() {
        List<CarDamage> result = carDamageRepository.findAll();
        List<CarDamageGetResponse> response = result.stream()
                .map(carDamage -> modelMapperService.forDto()
                        .map(carDamage, CarDamageGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>("Success, All Car Damages listed.", response);
    }

    @Override
    public DataResult<CarDamageGetResponse> getById(long id) throws BusinessException {
        checkCarDamageIdExist(id);

        CarDamage carDamage = carDamageRepository.getById(id);
        CarDamageGetResponse response = modelMapperService.forDto().map(carDamage, CarDamageGetResponse.class);

        return new SuccessDataResult<>("Success, Car Damage with requested ID found.", response);
    }

    @Override
    public Result update(long id, CarDamageUpdateRequest updateRequest) throws BusinessException {
        checkCarIdExist(updateRequest.getCarId());
        checkCarDamageIdExist(id);

        CarDamage carDamage = this.modelMapperService.forRequest().map(updateRequest, CarDamage.class);
        carDamage.setId(id);

        this.carDamageRepository.save(carDamage);

        return new SuccessResult("Success, Car Damage updated: " + carDamage);
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkCarDamageIdExist(id);

        this.carDamageRepository.deleteById(id);

        return new SuccessResult("Success, Brand deleted with requested ID: " + id);
    }

    private void checkCarDamageIdExist(long id) throws BusinessException {
        if (Objects.nonNull(carDamageRepository.findById(id)))
            throw new BusinessException("Can't find Car Damage with id: " + id);
    }

    private void checkCarIdExist(long carId) throws BusinessException {
        if (Objects.nonNull(carRepository.findById(carId)))
            throw new BusinessException("Can't find Car with id: " + carId);
    }
}
