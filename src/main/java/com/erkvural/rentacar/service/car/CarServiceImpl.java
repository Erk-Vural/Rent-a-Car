package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.constant.MessageStrings;
import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utils.results.*;
import com.erkvural.rentacar.dto.car.create.CarCreateRequest;
import com.erkvural.rentacar.dto.car.get.CarGetResponse;
import com.erkvural.rentacar.dto.car.update.CarUpdateRequest;
import com.erkvural.rentacar.entity.car.Car;
import com.erkvural.rentacar.repository.car.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapperService modelMapperService;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapperService modelMapperService) {
        this.carRepository = carRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CarCreateRequest createRequest) {

        Car car = this.modelMapperService.forRequest().map(createRequest, Car.class);
        this.carRepository.save(car);

        return new SuccessResult(MessageStrings.CARADD);
    }

    @Override
    public DataResult<List<CarGetResponse>> getAll() {
        List<Car> result = carRepository.findAll();

        List<CarGetResponse> response = result.stream()
                .map(car -> modelMapperService.forDto()
                        .map(car, CarGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.CARLIST, response);
    }

    @Override
    public DataResult<CarGetResponse> getById(long id) throws BusinessException {
        checkCarIdExist(id);

        Car car = carRepository.getById(id);
        CarGetResponse response = modelMapperService.forDto().map(car, CarGetResponse.class);

        return new SuccessDataResult<>(MessageStrings.CARFOUND, response);
    }

    @Override
    public DataResult<List<CarGetResponse>> getAllPaged(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        List<Car> result = this.carRepository.findAll(pageable).getContent();
        List<CarGetResponse> response = result.stream()
                .map(car -> this.modelMapperService.forDto()
                        .map(car, CarGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.CARMPAGED, response);
    }

    @Override
    public DataResult<List<CarGetResponse>> getAllSorted(Sort.Direction direction) {
        Sort s = Sort.by(direction, "dailyPrice");

        List<Car> result = this.carRepository.findAll(s);
        List<CarGetResponse> response = result.stream()
                .map(car -> this.modelMapperService.forDto()
                        .map(car, CarGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.CARSORTED, response);
    }

    @Override
    public DataResult<List<CarGetResponse>> getAllByDailyPriceLessThanEqual(double dailyPrice) {
        List<Car> result = this.carRepository.findByDailyPriceLessThanEqual(dailyPrice);

        if (result.isEmpty()) {
            return new ErrorDataResult<>("No results");
        }

        List<CarGetResponse> response = result.stream()
                .map(car -> this.modelMapperService.forDto()
                        .map(car, CarGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.CARGETLESSTHANEQUAL, response);
    }

    @Override
    public Result update(long id, CarUpdateRequest updateRequest) throws BusinessException {
        checkCarIdExist(id);

        Car car = this.modelMapperService.forRequest().map(updateRequest, Car.class);
        car.setId(id);

        this.carRepository.save(car);

        return new SuccessResult(MessageStrings.CARUPDATE);
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkCarIdExist(id);

        this.carRepository.deleteById(id);

        return new SuccessResult(MessageStrings.CARDELETE);
    }

    private void checkCarIdExist(long id) throws BusinessException {
        if (Objects.nonNull(carRepository.findById(id)))
            throw new BusinessException(MessageStrings.CARNOTFOUND);
    }
}
