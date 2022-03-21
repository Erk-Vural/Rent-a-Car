package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.core.utils.results.SuccessResult;
import com.erkvural.rentacar.dto.car.create.CarRentalCreateRequest;
import com.erkvural.rentacar.dto.car.get.CarRentalGetResponse;
import com.erkvural.rentacar.dto.car.update.CarRentalUpdateRequest;
import com.erkvural.rentacar.entity.car.CarMaintenance;
import com.erkvural.rentacar.entity.car.CarRental;
import com.erkvural.rentacar.repository.car.CarMaintenanceRepository;
import com.erkvural.rentacar.repository.car.CarRentalRepository;
import com.erkvural.rentacar.repository.car.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CarRentalServiceImpl implements CarRentalService {
    private final CarRentalRepository carRentalRepository;
    private final CarMaintenanceRepository carMaintenanceRepository;
    private final CarRepository carRepository;
    private final ModelMapperService modelMapperService;
    private final OrderedAdditionalServiceService orderedAdditionalServiceService;

    @Autowired
    public CarRentalServiceImpl(CarRentalRepository carRentalRepository, CarMaintenanceRepository carMaintenanceRepository, CarRepository carRepository, ModelMapperService modelMapperService, OrderedAdditionalServiceService orderedAdditionalServiceService) {
        this.carRentalRepository = carRentalRepository;
        this.carMaintenanceRepository = carMaintenanceRepository;
        this.carRepository = carRepository;
        this.modelMapperService = modelMapperService;
        this.orderedAdditionalServiceService = orderedAdditionalServiceService;
    }

    @Override
    public Result add(CarRentalCreateRequest createRequest) throws BusinessException {
        checkCarIdExist(createRequest.getCarId());
        checkUnderMaintenance(createRequest.getCarId());

        CarRental carRental = this.modelMapperService.forRequest().map(createRequest, CarRental.class);

        this.orderedAdditionalServiceService.add(createRequest.getOrderedAdditionalServiceCreateDtos(), carRental.getId());

        carRental.setOrderedAdditionalServices(this.orderedAdditionalServiceService.getByCarRentalId(carRental.getId()));

        // carRental.setBill(calRentedTotal(carRental.getId()));

        this.carRentalRepository.save(carRental);

        return new SuccessResult("Success, Car Rental added: " + carRental);
    }

    @Override
    public SuccessDataResult<List<CarRentalGetResponse>> getAll() {
        List<CarRental> result = carRentalRepository.findAll();

        List<CarRentalGetResponse> response = result.stream().map(carRental -> modelMapperService.forDto().map(carRental, CarRentalGetResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>("Success, All Car Rentals listed.", response);
    }

    @Override
    public SuccessDataResult<CarRentalGetResponse> getById(long id) throws BusinessException {
        checkCarRentalIdExist(id);

        CarRental carRental = carRentalRepository.findById(id);
        CarRentalGetResponse response = modelMapperService.forDto().map(carRental, CarRentalGetResponse.class);

        return new SuccessDataResult<>("Success, Car Rental with requested ID found.", response);
    }

    @Override
    public SuccessDataResult<List<CarRentalGetResponse>> getByCarId(long carId) {
        List<CarRental> result = this.carRentalRepository.findByCar_Id(carId);

        List<CarRentalGetResponse> response = result.stream().map(carRental -> this.modelMapperService.forDto().map(carRental, CarRentalGetResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>("Success,  Car Rental with requested carID found", response);
    }

    @Override
    public SuccessDataResult<List<CarRentalGetResponse>> getByCustomerId(long customerId) {
        List<CarRental> result = this.carRentalRepository.findByCustomer_UserId(customerId);

        List<CarRentalGetResponse> response = result.stream().map(carRental -> this.modelMapperService.forDto().map(carRental, CarRentalGetResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>("Success,  Car Rental with requested customerID found", response);
    }

    @Override
    public DataResult<List<CarRentalGetResponse>> getAllStartDateSorted(Sort.Direction direction) {
        Sort s = Sort.by(direction, "startDate");

        List<CarRental> result = this.carRentalRepository.findAll(s);
        List<CarRentalGetResponse> response = result.stream().map(rental -> this.modelMapperService.forDto().map(rental, CarRentalGetResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(response);
    }

    @Override
    public DataResult<List<CarRentalGetResponse>> getAllEndDateSorted(Sort.Direction direction) {
        Sort s = Sort.by(direction, "endDate");

        List<CarRental> result = this.carRentalRepository.findAll(s);
        List<CarRentalGetResponse> response = result.stream().map(rental -> this.modelMapperService.forDto().map(rental, CarRentalGetResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(response);
    }

    @Override
    public Result update(long id, CarRentalUpdateRequest updateRequest) throws BusinessException {
        checkCarRentalIdExist(id);
        checkUnderMaintenance(updateRequest.getCarId());

        CarRental carRental = this.modelMapperService.forRequest().map(updateRequest, CarRental.class);
        carRental.setId(id);

        this.carRentalRepository.save(carRental);

        return new SuccessResult("Car Rental updated: " + carRental);
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkCarRentalIdExist(id);

        this.carRepository.deleteById(id);

        return new SuccessResult("Success, Car Rental deleted with requested ID: " + id);
    }

    private void checkCarIdExist(long id) throws BusinessException {
        if (Objects.nonNull(carRepository.findById(id))) throw new BusinessException("Can't find Car with id: " + id);
    }

    private void checkCarRentalIdExist(long id) throws BusinessException {
        if (Objects.nonNull(carMaintenanceRepository.findById(id)))
            throw new BusinessException("Can't find Car Rental with id: " + id);
    }

    private void checkUnderMaintenance(long carId) throws BusinessException {
        List<CarMaintenance> result = this.carMaintenanceRepository.findByCar_Id(carId);
        if (result != null) {
            for (CarMaintenance carMaintenance : result) {
                if (carMaintenance.getReturnDate() != null) {
                    throw new BusinessException("Car is under maintenance until: " + carMaintenance.getReturnDate());
                }
            }
        }
    }

    private double calRentedTotal(long id) {

        CarRental carRental = carRentalRepository.findById(id);

        long totalDays = ChronoUnit.DAYS.between(carRental.getStartDate(), carRental.getEndDate());

        double carDailyPrice = carRental.getCar().getDailyPrice();

        double OrderedAdditionalServicesDailyPrice = this.orderedAdditionalServiceService.calDailyTotal(carRental.getOrderedAdditionalServices());

        double dailyTotal = carDailyPrice + OrderedAdditionalServicesDailyPrice;

        return (dailyTotal * totalDays) + checkCityIds(carRental);
    }

    private double checkCityIds(CarRental carRental) {
        if (carRental.getRentedCity().getId().equals(carRental.getReturnedCity().getId())) {
            return 750.0;
        }
        return 0.0;
    }
}
