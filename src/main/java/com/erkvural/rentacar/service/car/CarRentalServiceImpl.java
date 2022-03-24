package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.constant.MessageStrings;
import com.erkvural.rentacar.constant.CarStatus;
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
    public Result add(CarRentalCreateRequest createRequest) throws BusinessException {
        checkCarIdExist(createRequest.getCarId());
        checkCarStatus(createRequest.getCarId());

        CarRental carRental = this.modelMapperService.forRequest().map(createRequest, CarRental.class);

        this.orderedAdditionalServiceService.add(createRequest.getOrderedAdditionalServiceCreateRequestSet(), carRental.getId());

        carRental.setOrderedAdditionalServices(this.orderedAdditionalServiceService.getByCarRentalId(carRental.getId()));

        this.repository.save(carRental);

        return new SuccessResult(MessageStrings.RENTALADD);
    }

    @Override
    public SuccessDataResult<List<CarRentalGetResponse>> getAll() {
        List<CarRental> result = repository.findAll();

        List<CarRentalGetResponse> response = result.stream().map(carRental -> modelMapperService.forResponse().map(carRental, CarRentalGetResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.RENTALLIST, response);
    }

    @Override
    public SuccessDataResult<CarRentalGetResponse> getById(long id) throws BusinessException {
        checkCarRentalIdExist(id);

        CarRental carRental = repository.findById(id);
        CarRentalGetResponse response = modelMapperService.forResponse().map(carRental, CarRentalGetResponse.class);

        return new SuccessDataResult<>(MessageStrings.RENTALFOUND, response);
    }

    @Override
    public SuccessDataResult<List<CarRentalGetResponse>> getByCarId(long carId) {
        List<CarRental> result = this.repository.findByCar_Id(carId);

        List<CarRentalGetResponse> response = result.stream().map(carRental -> this.modelMapperService.forResponse().map(carRental, CarRentalGetResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.RENTALFOUNDCARID, response);
    }

    @Override
    public SuccessDataResult<List<CarRentalGetResponse>> getByCustomerId(long customerId) {
        List<CarRental> result = this.repository.findByCustomer_UserId(customerId);

        List<CarRentalGetResponse> response = result.stream().map(carRental -> this.modelMapperService.forResponse().map(carRental, CarRentalGetResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.RENTALFOUNDCUSTOMERID, response);
    }

    @Override
    public DataResult<List<CarRentalGetResponse>> getAllStartDateSorted(Sort.Direction direction) {
        Sort s = Sort.by(direction, "startDate");

        List<CarRental> result = this.repository.findAll(s);
        List<CarRentalGetResponse> response = result.stream().map(rental -> this.modelMapperService.forResponse().map(rental, CarRentalGetResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.RENTALSTARTDATESORTED, response);
    }

    @Override
    public DataResult<List<CarRentalGetResponse>> getAllEndDateSorted(Sort.Direction direction) {
        Sort s = Sort.by(direction, "endDate");

        List<CarRental> result = this.repository.findAll(s);
        List<CarRentalGetResponse> response = result.stream().map(rental -> this.modelMapperService.forResponse().map(rental, CarRentalGetResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.RENTALENDDATESORTED, response);
    }

    @Override
    public Result update(long id, CarRentalUpdateRequest updateRequest) throws BusinessException {
        checkCarRentalIdExist(id);
        checkCarStatus(updateRequest.getCarId());

        CarRental carRental = this.modelMapperService.forRequest().map(updateRequest, CarRental.class);
        carRental.setId(id);

        this.repository.save(carRental);

        return new SuccessResult(MessageStrings.RENTALUPDATE);
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkCarRentalIdExist(id);

        this.repository.deleteById(id);

        return new SuccessResult(MessageStrings.RENTALDELETE);
    }

    private void checkCarRentalIdExist(long id) throws BusinessException {
        if (Objects.nonNull(repository.findById(id)))
            throw new BusinessException(MessageStrings.RENTALNOTFOUND);
    }

    private void checkCarIdExist(long carId) throws BusinessException {
        if (Objects.nonNull(carService.getById(carId))) throw new BusinessException(MessageStrings.CARNOTFOUND);
    }

    private void checkCarStatus(long carId) throws BusinessException {
        if (this.carService.getById(carId).getData().getStatus() == CarStatus.RENTED)
            throw new BusinessException(MessageStrings.RENTALALREADYRENTED);
        else if (this.carService.getById(carId).getData().getStatus() == CarStatus.UNDER_MAINTENANCE)
            throw new BusinessException(MessageStrings.RENTALMAINTENANCEERROR);
        else if (this.carService.getById(carId).getData().getStatus() == CarStatus.DAMAGED)
            throw new BusinessException(MessageStrings.RENTALDAMAGEDERROR);
    }

    @Override
    public double calRentedTotal(long id) {
        CarRental carRental = repository.findById(id);

        return (carRental.getCar().getDailyPrice()
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

        return ChronoUnit.DAYS.between(startDate, endDate);
    }
}
