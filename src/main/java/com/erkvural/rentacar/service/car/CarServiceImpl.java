package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utils.results.*;
import com.erkvural.rentacar.dto.car.create.CarCreateDto;
import com.erkvural.rentacar.dto.car.get.CarGetDto;
import com.erkvural.rentacar.dto.car.update.CarUpdateDto;
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
    public Result add(CarCreateDto carCreateDto) {

        Car car = this.modelMapperService.forRequest().map(carCreateDto, Car.class);
        this.carRepository.save(car);

        return new SuccessResult("Success, Car added: " + car);
    }

    @Override
    public DataResult<List<CarGetDto>> getAll() {
        List<Car> result = carRepository.findAll();

        List<CarGetDto> response = result.stream()
                .map(car -> modelMapperService.forDto()
                        .map(car, CarGetDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>("Success, All Cars listed.", response);
    }

    @Override
    public DataResult<CarGetDto> getById(long id) throws BusinessException {
        checkCarIdExist(id);

        Car car = carRepository.getById(id);
        CarGetDto response = modelMapperService.forDto().map(car, CarGetDto.class);

        return new SuccessDataResult<>("Success, Car with requested ID found.", response);
    }

    @Override
    public DataResult<List<CarGetDto>> getAllPaged(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        List<Car> result = this.carRepository.findAll(pageable).getContent();
        List<CarGetDto> response = result.stream()
                .map(car -> this.modelMapperService.forDto()
                        .map(car, CarGetDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>("GetAllPaged Results Listed.", response);
    }

    @Override
    public DataResult<List<CarGetDto>> getAllSorted(Sort.Direction direction) {
        Sort s = Sort.by(direction, "dailyPrice");

        List<Car> result = this.carRepository.findAll(s);
        List<CarGetDto> response = result.stream()
                .map(car -> this.modelMapperService.forDto()
                        .map(car, CarGetDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>("GetAllSorted Results Listed.", response);
    }

    @Override
    public DataResult<List<CarGetDto>> getAllByDailyPriceLessThanEqual(double dailyPrice) {
        List<Car> result = this.carRepository.findByDailyPriceLessThanEqual(dailyPrice);

        if (result.isEmpty()) {
            return new ErrorDataResult<>("No results");
        }

        List<CarGetDto> response = result.stream()
                .map(car -> this.modelMapperService.forDto()
                        .map(car, CarGetDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>("GetAllByDailyPriceLessThanEqual Results Listed.", response);
    }

    @Override
    public Result update(long id, CarUpdateDto carUpdateDto) throws BusinessException {
        checkCarIdExist(id);

        Car car = this.modelMapperService.forRequest().map(carUpdateDto, Car.class);
        this.carRepository.save(car);

        return new SuccessResult("Success, Car updated: " + car);
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkCarIdExist(id);

        this.carRepository.deleteById(id);

        return new SuccessResult("Success, Car deleted with requested ID: " + id);
    }

    private void checkCarIdExist(long id) throws BusinessException {
        if (Objects.nonNull(carRepository.findById(id)))
            throw new BusinessException("Can't find Car with id: " + id);
    }
}
