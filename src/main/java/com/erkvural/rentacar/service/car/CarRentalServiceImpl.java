package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.constant.CarStatus;
import com.erkvural.rentacar.constant.MessageStrings;
import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.core.utils.results.SuccessResult;
import com.erkvural.rentacar.dto.car.create.CarRentalCreateRequest;
import com.erkvural.rentacar.dto.car.get.CarRentalGetResponse;
import com.erkvural.rentacar.dto.car.update.CarRentalUpdateRequest;
import com.erkvural.rentacar.entity.car.CarRental;
import com.erkvural.rentacar.repository.car.CarRentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CarRentalServiceImpl implements CarRentalService {
    private final CarRentalRepository repository;
    private final ModelMapperService modelMapperService;
    private final OrderedAdditionalServiceService orderedAdditionalServiceService;
    private final CarService carService;

    @Autowired
    public CarRentalServiceImpl(CarRentalRepository repository, ModelMapperService modelMapperService, OrderedAdditionalServiceService orderedAdditionalServiceService, CarService carService) {
        this.repository = repository;
        this.modelMapperService = modelMapperService;
        this.orderedAdditionalServiceService = orderedAdditionalServiceService;
        this.carService = carService;
    }

    @Override
    public Result add(CarRentalCreateRequest createRequest) {
        checkCarIdExist(createRequest.getCarId());
        checkCarStatus(createRequest.getCarId());

        CarRental carRental = this.modelMapperService.forRequest().map(createRequest, CarRental.class);

        this.orderedAdditionalServiceService.add(createRequest.getOrderedAdditionalServiceCreateRequestSet());

        carRental.setOrderedAdditionalServices(this.orderedAdditionalServiceService.getByCarRentalId(carRental.getId()));

        carService.setCarStatus(CarStatus.RENTED, createRequest.getCarId());

        this.repository.save(carRental);

        return new SuccessResult(MessageStrings.RENTAL_ADDED);
    }

    @Override
    public SuccessDataResult<List<CarRentalGetResponse>> getAll() {
        List<CarRental> result = repository.findAll();

        List<CarRentalGetResponse> response = result.stream().map(carRental -> modelMapperService.forResponse().map(carRental, CarRentalGetResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.RENTALS_LISTED, response);
    }

    @Override
    public SuccessDataResult<CarRentalGetResponse> getById(long id) {
        checkCarRentalIdExist(id);

        CarRental carRental = repository.findById(id);
        CarRentalGetResponse response = modelMapperService.forResponse().map(carRental, CarRentalGetResponse.class);

        return new SuccessDataResult<>(MessageStrings.RENTAL_FOUND, response);
    }

    @Override
    public SuccessDataResult<List<CarRentalGetResponse>> getByCarId(long carId) {
        List<CarRental> result = this.repository.findByCar_Id(carId);

        List<CarRentalGetResponse> response = result.stream().map(carRental -> this.modelMapperService.forResponse().map(carRental, CarRentalGetResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.RENTAL_FOUND_BY_CAR_ID, response);
    }

    @Override
    public SuccessDataResult<List<CarRentalGetResponse>> getByCustomerId(long customerId) {
        List<CarRental> result = this.repository.findByCustomer_UserId(customerId);

        List<CarRentalGetResponse> response = result.stream().map(carRental -> this.modelMapperService.forResponse().map(carRental, CarRentalGetResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.RENTAL_FOUND_BY_CUSTOMER_ID, response);
    }

    @Override
    public DataResult<List<CarRentalGetResponse>> getAllStartDateSorted(Sort.Direction direction) {
        Sort s = Sort.by(direction, "startDate");

        List<CarRental> result = this.repository.findAll(s);
        List<CarRentalGetResponse> response = result.stream().map(rental -> this.modelMapperService.forResponse().map(rental, CarRentalGetResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.RENTAL_START_DATE_SORTED, response);
    }

    @Override
    public DataResult<List<CarRentalGetResponse>> getAllEndDateSorted(Sort.Direction direction) {
        Sort s = Sort.by(direction, "endDate");

        List<CarRental> result = this.repository.findAll(s);
        List<CarRentalGetResponse> response = result.stream().map(rental -> this.modelMapperService.forResponse().map(rental, CarRentalGetResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.RENTAL_END_DATE_SORTED, response);
    }

    @Override
    public Result update(long id, CarRentalUpdateRequest updateRequest) {
        checkCarRentalIdExist(id);
        checkCarStatus(updateRequest.getCarId());

        CarRental carRental = this.modelMapperService.forRequest().map(updateRequest, CarRental.class);
        carRental.setId(id);
        carService.setCarStatus(CarStatus.RENTED, updateRequest.getCarId());
        carService.setMileage(updateRequest.getEndMileage(), updateRequest.getCarId());

        this.repository.save(carRental);

        return new SuccessResult(MessageStrings.RENTAL_UPDATED);
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkCarRentalIdExist(id);

        carService.setCarStatus(CarStatus.AVAILABLE, repository.findById(id).getCar().getId());

        this.repository.deleteById(id);

        return new SuccessResult(MessageStrings.RENTAL_DELETED);
    }

    private void checkCarRentalIdExist(long id) throws BusinessException {
        if (!Objects.nonNull(repository.findById(id)))
            throw new BusinessException(MessageStrings.RENTAL_NOT_FOUND);
    }

    private void checkCarIdExist(long carId) throws BusinessException {
        if (!Objects.nonNull(carService.getById(carId).getData()))
            throw new BusinessException(MessageStrings.CAR_NOT_FOUND);
    }

    private void checkCarStatus(long carId) throws BusinessException {
        if (this.carService.getById(carId).getData().getStatus() == CarStatus.RENTED)
            throw new BusinessException(MessageStrings.RENTED_CAR_IS_RENTED);

        else if (this.carService.getById(carId).getData().getStatus() == CarStatus.UNDER_MAINTENANCE)
            throw new BusinessException(MessageStrings.RENTED_CAR_IS_UNDER_MAINTENANCE);

        else if (this.carService.getById(carId).getData().getStatus() == CarStatus.DAMAGED)
            throw new BusinessException(MessageStrings.RENTED_CAR_IS_DAMAGED);
    }

    @Override
    public double calRentedTotal(long id) {
        CarRental carRental = repository.findById(id);

        return (repository.findById(id).getCar().getDailyPrice()
                + this.orderedAdditionalServiceService.calDailyTotal(carRental.getOrderedAdditionalServices())
                * calTotalDays(carRental.getStartDate(), carRental.getEndDate()))
                + checkCityIds(carRental);
    }

    private double checkCityIds(CarRental carRental) {
        if (carRental.getRentedCity().getId().equals(carRental.getReturnedCity().getId())) {
            return 750.0;
        }
        return 0.0;
    }

    private long calTotalDays(LocalDate startDate, LocalDate endDate) {

        return ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }
}
