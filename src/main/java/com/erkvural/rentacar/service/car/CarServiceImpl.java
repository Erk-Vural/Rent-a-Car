package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.constant.MessageStrings;
import com.erkvural.rentacar.core.enums.CarStatus;
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
    public Result add(CarCreateRequest createRequest) throws BusinessException {
        checkBrandIdExist(createRequest.getBrandId());
        checkColorIdExist(createRequest.getColorId());

        Car car = this.modelMapperService.forRequest().map(createRequest, Car.class);
        car.setStatus(CarStatus.AVAILABLE);

        this.repository.save(car);

        return new SuccessResult(MessageStrings.CARADD);
    }

    @Override
    public DataResult<List<CarGetResponse>> getAll() {
        List<Car> result = repository.findAll();

        List<CarGetResponse> response = result.stream()
                .map(car -> modelMapperService.forResponse()
                        .map(car, CarGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.CARLIST, response);
    }

    @Override
    public DataResult<CarGetResponse> getById(long id) throws BusinessException {
        checkCarIdExist(id);

        Car car = repository.findById(id);
        CarGetResponse response = modelMapperService.forResponse().map(car, CarGetResponse.class);

        return new SuccessDataResult<>(MessageStrings.CARFOUND, response);
    }

    @Override
    public DataResult<List<CarGetResponse>> getAllPaged(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        List<Car> result = this.repository.findAll(pageable).getContent();
        List<CarGetResponse> response = result.stream()
                .map(car -> this.modelMapperService.forResponse()
                        .map(car, CarGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.CARPAGED, response);
    }

    @Override
    public DataResult<List<CarGetResponse>> getAllSorted(Sort.Direction direction) {
        Sort s = Sort.by(direction, "dailyPrice");

        List<Car> result = this.repository.findAll(s);
        List<CarGetResponse> response = result.stream()
                .map(car -> this.modelMapperService.forResponse()
                        .map(car, CarGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.CARSORTED, response);
    }

    @Override
    public DataResult<List<CarGetResponse>> getAllByDailyPriceLessThanEqual(double dailyPrice) {
        List<Car> result = this.repository.findByDailyPriceLessThanEqual(dailyPrice);

        if (result.isEmpty()) {
            return new ErrorDataResult<>(MessageStrings.CARNOTFOUND);
        }

        List<CarGetResponse> response = result.stream()
                .map(car -> this.modelMapperService.forResponse()
                        .map(car, CarGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.CARGETLESSTHANEQUAL, response);
    }

    @Override
    public Result update(long id, CarUpdateRequest updateRequest) throws BusinessException {
        checkBrandIdExist(updateRequest.getBrandId());
        checkColorIdExist(updateRequest.getColorId());
        checkCarIdExist(id);

        Car car = this.modelMapperService.forRequest().map(updateRequest, Car.class);
        car.setStatus(CarStatus.AVAILABLE);
        car.setId(id);

        this.repository.save(car);

        return new SuccessResult(MessageStrings.CARUPDATE);
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkCarIdExist(id);

        this.repository.deleteById(id);

        return new SuccessResult(MessageStrings.CARDELETE);
    }

    @Override
    public void setCarStatus(CarStatus status, long carId) throws BusinessException {
        checkCarIdExist(carId);

        Car car = repository.findById(carId);
        car.setStatus(status);

        new SuccessResult(MessageStrings.CARSTATUSSETTED);
    }

    private void checkCarIdExist(long id) throws BusinessException {
        if (Objects.nonNull(repository.findById(id)))
            throw new BusinessException(MessageStrings.CARNOTFOUND);
    }

    private void checkBrandIdExist(long brandId) throws BusinessException {
        if (Objects.nonNull(brandService.getById(brandId)))
            throw new BusinessException(MessageStrings.BRANDNOTFOUND);
    }

    private void checkColorIdExist(long colorId) throws BusinessException {
        if (Objects.nonNull(colorService.getById(colorId)))
            throw new BusinessException(MessageStrings.COLORNOTFOUND);
    }
}
