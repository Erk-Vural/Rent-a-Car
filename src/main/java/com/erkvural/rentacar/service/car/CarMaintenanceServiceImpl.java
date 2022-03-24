package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.constant.MessageStrings;
import com.erkvural.rentacar.constant.CarStatus;
import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.core.utils.results.SuccessResult;
import com.erkvural.rentacar.dto.car.create.CarMaintenanceCreateRequest;
import com.erkvural.rentacar.dto.car.get.CarMaintenanceGetResponse;
import com.erkvural.rentacar.dto.car.update.CarMaintenanceUpdateRequest;
import com.erkvural.rentacar.entity.car.CarMaintenance;
import com.erkvural.rentacar.repository.car.CarMaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CarMaintenanceServiceImpl implements CarMaintenanceService {
    private final CarMaintenanceRepository repository;
    private final ModelMapperService modelMapperService;
    private final CarService carService;

    @Autowired
    public CarMaintenanceServiceImpl(CarMaintenanceRepository repository, ModelMapperService modelMapperService, CarService carService) {
        this.repository = repository;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
    }

    @Override
    public Result add(CarMaintenanceCreateRequest createRequest) throws BusinessException {
        checkCarIdExist(createRequest.getCarId());
        checkCarStatus(createRequest.getCarId());

        CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(createRequest, CarMaintenance.class);

        carService.setCarStatus(CarStatus.UNDER_MAINTENANCE, createRequest.getCarId());

        this.repository.save(carMaintenance);

        return new SuccessResult(MessageStrings.CAR_MAINTENANCE_ADDED);
    }

    @Override
    public DataResult<List<CarMaintenanceGetResponse>> getAll() {
        List<CarMaintenance> result = repository.findAll();

        List<CarMaintenanceGetResponse> response = result.stream()
                .map(carMaintenance -> modelMapperService.forResponse()
                        .map(carMaintenance, CarMaintenanceGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.CAR_MAINTENANCES_LISTED, response);
    }

    @Override
    public DataResult<CarMaintenanceGetResponse> getById(long id) throws BusinessException {
        checkCarMaintenanceIdExist(id);

        CarMaintenance carMaintenance = repository.findById(id);
        CarMaintenanceGetResponse response = modelMapperService.forResponse().map(carMaintenance, CarMaintenanceGetResponse.class);

        return new SuccessDataResult<>(MessageStrings.CAR_MAINTENANCE_FOUND, response);
    }

    @Override
    public SuccessDataResult<List<CarMaintenanceGetResponse>> getByCarId(long carId) {

        List<CarMaintenance> result = this.repository.findByCar_Id(carId);

        List<CarMaintenanceGetResponse> response = result.stream().map(carMaintenance -> this.modelMapperService.forResponse()
                .map(carMaintenance, CarMaintenanceGetResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.CAR_MAINTENANCE_FOUND_BY_CAR_ID, response);
    }

    @Override
    public DataResult<List<CarMaintenanceGetResponse>> getAllPaged(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        List<CarMaintenance> result = this.repository.findAll(pageable).getContent();
        List<CarMaintenanceGetResponse> response = result.stream()
                .map(carMaintenance -> this.modelMapperService.forResponse()
                        .map(carMaintenance, CarMaintenanceGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.CAR_MAINTENANCES_PAGED, response);
    }

    @Override
    public DataResult<List<CarMaintenanceGetResponse>> getAllSorted(Sort.Direction direction) {
        Sort s = Sort.by(direction, "returnDate");

        List<CarMaintenance> result = this.repository.findAll(s);
        List<CarMaintenanceGetResponse> response = result.stream()
                .map(carMaintenance -> this.modelMapperService.forResponse()
                        .map(carMaintenance, CarMaintenanceGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.CAR_MAINTENANCES_SORTED, response);
    }

    @Override
    public Result update(long id, CarMaintenanceUpdateRequest updateRequest) throws BusinessException {
        checkCarMaintenanceIdExist(id);
        checkCarStatus(updateRequest.getCarId());

        CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(updateRequest, CarMaintenance.class);

        carService.setCarStatus(CarStatus.UNDER_MAINTENANCE, updateRequest.getCarId());
        carMaintenance.setId(id);

        this.repository.save(carMaintenance);

        return new SuccessResult(MessageStrings.CAR_MAINTENANCE_UPDATED);
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkCarMaintenanceIdExist(id);

        carService.setCarStatus(CarStatus.AVAILABLE, repository.findById(id).getCar().getId());

        this.repository.deleteById(id);

        return new SuccessResult(MessageStrings.CAR_MAINTENANCE_DELETED);
    }

    private void checkCarIdExist(long carId) throws BusinessException {
        if (Objects.nonNull(carService.getById(carId).getData()))
            throw new BusinessException(MessageStrings.CAR_NOT_FOUND);
    }

    private void checkCarMaintenanceIdExist(long id) throws BusinessException {
        if (Objects.nonNull(repository.findById(id)))
            throw new BusinessException(MessageStrings.CAR_MAINTENANCE_NOT_FOUND);
    }

    private void checkCarStatus(long carId) throws BusinessException {
        if (this.carService.getById(carId).getData().getStatus() == CarStatus.RENTED)
            throw new BusinessException(MessageStrings.CAR_MAINTENANCE_RENTAL_ERROR);
        else if (this.carService.getById(carId).getData().getStatus() == CarStatus.UNDER_MAINTENANCE)
            throw new BusinessException(MessageStrings.CAR_IS_UNDER_MAINTENANCE);
    }
}
