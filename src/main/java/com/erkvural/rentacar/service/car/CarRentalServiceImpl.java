package com.erkvural.rentacar.service.car;

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
import com.erkvural.rentacar.entity.car.CarMaintenance;
import com.erkvural.rentacar.entity.car.CarRental;
import com.erkvural.rentacar.repository.car.CarMaintenanceRepository;
import com.erkvural.rentacar.repository.car.CarRentalRepository;
import com.erkvural.rentacar.repository.car.CarRepository;
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
    private final CarMaintenanceRepository carMaintenanceRepository;
    private final CarRepository carRepository;
    private final ModelMapperService modelMapperService;
    private final OrderedAdditionalServiceService orderedAdditionalServiceService;

    @Autowired
    public CarRentalServiceImpl(CarRentalRepository repository, CarMaintenanceRepository carMaintenanceRepository, CarRepository carRepository, ModelMapperService modelMapperService, OrderedAdditionalServiceService orderedAdditionalServiceService) {
        this.repository = repository;
        this.carMaintenanceRepository = carMaintenanceRepository;
        this.carRepository = carRepository;
        this.modelMapperService = modelMapperService;
        this.orderedAdditionalServiceService = orderedAdditionalServiceService;
    }

    @Override
    public Result add(CarRentalCreateRequest createRequest) throws BusinessException {
        checkCarIdExist(createRequest.getCarId());
        checkUnderMaintenance(createRequest.getCarId());
        checkIfCarIsRented(createRequest.getCarId(), createRequest.getStartDate());

        CarRental carRental = this.modelMapperService.forRequest().map(createRequest, CarRental.class);

        this.orderedAdditionalServiceService.add(createRequest.getOrderedAdditionalServiceCreateRequestSet(), carRental.getId());

        carRental.setOrderedAdditionalServices(this.orderedAdditionalServiceService.getByCarRentalId(carRental.getId()));

        // carRental.setBill(calRentedTotal(carRental.getId()));

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
        checkUnderMaintenance(updateRequest.getCarId());

        CarRental carRental = this.modelMapperService.forRequest().map(updateRequest, CarRental.class);
        carRental.setId(id);

        this.repository.save(carRental);

        return new SuccessResult(MessageStrings.RENTALUPDATE);
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkCarRentalIdExist(id);

        this.carRepository.deleteById(id);

        return new SuccessResult(MessageStrings.RENTALDELETE);
    }

    private void checkCarIdExist(long id) throws BusinessException {
        if (Objects.nonNull(carRepository.findById(id))) throw new BusinessException(MessageStrings.CARNOTFOUND);
    }

    private void checkCarRentalIdExist(long id) throws BusinessException {
        if (Objects.nonNull(repository.findById(id)))
            throw new BusinessException(MessageStrings.RENTALNOTFOUND);
    }

    private void checkUnderMaintenance(long carId) throws BusinessException {
        List<CarMaintenance> result = this.carMaintenanceRepository.findByCar_Id(carId);
        if (result != null) {
            for (CarMaintenance carMaintenance : result) {
                if (carMaintenance.getReturnDate() != null) {
                    throw new BusinessException(MessageStrings.RENTALMAINTENANCEERROR);
                }
            }
        }
    }

    private void checkIfCarIsRented(long carId, LocalDate startingDate) throws BusinessException {

        List<CarRental> carRentals = this.repository.findByCar_Id(carId);

        for (CarRental carRental : carRentals) {

            if (startingDate.isAfter(carRental.getEndDate())) {

                throw new BusinessException(MessageStrings.RENTALALREADYRENTED);
            }
        }
    }

    private double calRentedTotal(long id) {

        CarRental carRental = repository.findById(id);

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
