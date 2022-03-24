package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.mapping.ModelMapperService;
import com.erkvural.rentacar.dto.car.create.OrderedAdditionalServiceCreateRequest;
import com.erkvural.rentacar.entity.car.OrderedAdditionalService;
import com.erkvural.rentacar.repository.car.OrderedAdditionalServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrderedAdditionalServiceServiceImpl implements OrderedAdditionalServiceService {
    private final OrderedAdditionalServiceRepository repository;
    private final ModelMapperService modelMapperService;


    @Autowired
    public OrderedAdditionalServiceServiceImpl(OrderedAdditionalServiceRepository repository, ModelMapperService modelMapperService) {
        this.repository = repository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public void add(Set<OrderedAdditionalServiceCreateRequest> createRequestSet, long carRentalId) throws BusinessException {
        for (OrderedAdditionalServiceCreateRequest createRequest : createRequestSet) {

            OrderedAdditionalService orderedAdditionalService = this.modelMapperService.forRequest().map(createRequest, OrderedAdditionalService.class);

            this.repository.save(orderedAdditionalService);
        }
    }

    @Override
    public Set<OrderedAdditionalService> getByCarRentalId(long carRentalId) {
        return this.repository.findByCarRental_Id(carRentalId);
    }

    @Override
    public Double calDailyTotal(Set<OrderedAdditionalService> orderedAdditionalServices) {
        double dailyTotal = 0;

        for (OrderedAdditionalService orderedAdditionalService : orderedAdditionalServices) {

            dailyTotal += orderedAdditionalService.getQuantity() * orderedAdditionalService.getAdditionalService().getDailyPrice();
        }

        return dailyTotal;
    }
}
