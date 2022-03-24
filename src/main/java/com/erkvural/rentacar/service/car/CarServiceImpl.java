package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.constant.MessageStrings;
import com.erkvural.rentacar.constant.CarStatus;
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
    private final CarRepository repository;
    private final ModelMapperService modelMapperService;
    private final ColorService colorService;
    private final BrandService brandService;

    @Autowired
    public CarServiceImpl(CarRepository repository, ModelMapperService modelMapperService, ColorService colorService, BrandService brandService) {
        this.repository = repository;
        this.modelMapperService = modelMapperService;
        this.colorService = colorService;
        this.brandService = brandService;
    }

    @Override
    public Result add(CarCreateRequest createRequest){
        checkBrandIdExist(createRequest.getBrandId());
        checkColorIdExist(createRequest.getColorId());

        Car car = this.modelMapperService.forRequest().map(createRequest, Car.class);
        car.setStatus(CarStatus.AVAILABLE);

        this.repository.save(car);

        return new SuccessResult(MessageStrings.CAR_ADDED);
    }

    @Override
    public DataResult<List<CarGetResponse>> getAll() {
        List<Car> result = repository.findAll();

        List<CarGetResponse> response = result.stream()
                .map(car -> modelMapperService.forResponse()
                        .map(car, CarGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.CARS_LISTED, response);
    }

    @Override
    public DataResult<CarGetResponse> getById(long id){
        checkCarIdExist(id);

        Car car = repository.findById(id);
        CarGetResponse response = modelMapperService.forResponse().map(car, CarGetResponse.class);

        return new SuccessDataResult<>(MessageStrings.CAR_FOUND, response);
    }

    @Override
    public DataResult<List<CarGetResponse>> getAllPaged(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        List<Car> result = this.repository.findAll(pageable).getContent();
        List<CarGetResponse> response = result.stream()
                .map(car -> this.modelMapperService.forResponse()
                        .map(car, CarGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.CAR_PAGED, response);
    }

    @Override
    public DataResult<List<CarGetResponse>> getAllSorted(Sort.Direction direction) {
        Sort s = Sort.by(direction, "dailyPrice");

        List<Car> result = this.repository.findAll(s);
        List<CarGetResponse> response = result.stream()
                .map(car -> this.modelMapperService.forResponse()
                        .map(car, CarGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.CAR_SORTED, response);
    }

    @Override
    public DataResult<List<CarGetResponse>> getAllByDailyPriceLessThanEqual(double dailyPrice) {
        List<Car> result = this.repository.findByDailyPriceLessThanEqual(dailyPrice);

        if (result.isEmpty()) {
            return new ErrorDataResult<>(MessageStrings.CAR_NOT_FOUND);
        }

        List<CarGetResponse> response = result.stream()
                .map(car -> this.modelMapperService.forResponse()
                        .map(car, CarGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.CARS_LISTED_LESS_THAN_EQUAL, response);
    }

    @Override
    public Result update(long id, CarUpdateRequest updateRequest){
        checkBrandIdExist(updateRequest.getBrandId());
        checkColorIdExist(updateRequest.getColorId());
        checkCarIdExist(id);

        Car car = this.modelMapperService.forRequest().map(updateRequest, Car.class);
        car.setStatus(CarStatus.AVAILABLE);
        car.setId(id);

        this.repository.save(car);

        return new SuccessResult(MessageStrings.CAR_UPDATED);
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkCarIdExist(id);

        this.repository.deleteById(id);

        return new SuccessResult(MessageStrings.CAR_DELETED);
    }

    @Override
    public void setCarStatus(CarStatus status, long carId) throws BusinessException {
        checkCarIdExist(carId);

        Car car = repository.findById(carId);
        car.setStatus(status);

        new SuccessResult(MessageStrings.CAR_STATUS_SET);
    }

    @Override
    public void setMileage(double endMileage, long carId) throws BusinessException {
        checkCarIdExist(carId);

        Car car = repository.findById(carId);
        car.setMileage(endMileage);

        new SuccessResult(MessageStrings.CAR_MILEAGE_SET);
    }

    private void checkCarIdExist(long id) throws BusinessException {
        if (Objects.nonNull(repository.findById(id)))
            throw new BusinessException(MessageStrings.CAR_NOT_FOUND);
    }

    private void checkBrandIdExist(long brandId) throws BusinessException {
        if (Objects.nonNull(brandService.getById(brandId).getData()))
            throw new BusinessException(MessageStrings.BRAND_NOT_FOUND);
    }

    private void checkColorIdExist(long colorId) throws BusinessException {
        if (Objects.nonNull(colorService.getById(colorId).getData()))
            throw new BusinessException(MessageStrings.COLOR_NOT_FOUND);
    }
}
