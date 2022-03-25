package com.erkvural.rentacar.service.customer;

import com.erkvural.rentacar.constant.MessageStrings;
import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.core.utils.results.SuccessResult;
import com.erkvural.rentacar.dto.customer.create.IndividualCustomerCreateRequest;
import com.erkvural.rentacar.dto.customer.get.IndividualCustomerGetResponse;
import com.erkvural.rentacar.dto.customer.update.IndividualCustomerUpdateRequest;
import com.erkvural.rentacar.entity.customer.IndividualCustomer;
import com.erkvural.rentacar.repository.customer.IndividualCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class IndividualCustomerServiceImpl implements IndividualCustomerService {

    private final IndividualCustomerRepository repository;
    private final ModelMapperService modelMapperService;

    @Autowired
    public IndividualCustomerServiceImpl(IndividualCustomerRepository repository, ModelMapperService modelMapperService) {
        this.repository = repository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(IndividualCustomerCreateRequest createRequest) {
        checkEmailExist(createRequest.getEmail());
        checkNationalIdExist(createRequest.getNationalId());

        IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(createRequest, IndividualCustomer.class);
        this.repository.save(individualCustomer);

        return new SuccessResult(MessageStrings.CUSTOMER_ADDED);
    }

    @Override
    public DataResult<List<IndividualCustomerGetResponse>> getAll() {
        List<IndividualCustomer> result = repository.findAll();

        List<IndividualCustomerGetResponse> response = result.stream()
                .map(individualCustomer -> modelMapperService.forResponse()
                        .map(individualCustomer, IndividualCustomerGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.CUSTOMERS_LISTED, response);
    }

    @Override
    public DataResult<IndividualCustomerGetResponse> getById(long id) {
        checkUserIdExist(id);

        IndividualCustomer individualCustomer = repository.getById(id);
        IndividualCustomerGetResponse response = modelMapperService.forResponse().map(individualCustomer, IndividualCustomerGetResponse.class);

        return new SuccessDataResult<>(MessageStrings.CUSTOMER_FOUND, response);
    }

    @Override
    public Result update(long id, IndividualCustomerUpdateRequest updateRequest) {
        checkUserIdExist(id);
        checkEmailExist(updateRequest.getEmail());
        checkNationalIdExist(updateRequest.getNationalId());

        IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(updateRequest, IndividualCustomer.class);
        individualCustomer.setUserId(id);

        this.repository.save(individualCustomer);

        return new SuccessResult(MessageStrings.CUSTOMER_UPDATED);
    }

    @Override
    public Result delete(long id) {
        checkUserIdExist(id);

        this.repository.deleteById(id);

        return new SuccessResult(MessageStrings.CUSTOMER_DELETED);
    }

    private void checkUserIdExist(long userId) throws BusinessException {
        if (!Objects.nonNull(repository.findByUserId(userId)))
            throw new BusinessException(MessageStrings.CUSTOMER_NOT_FOUND);
    }

    private void checkEmailExist(String email) throws BusinessException {
        if (Objects.nonNull(repository.findByEmail(email)))
            throw new BusinessException(MessageStrings.CUSTOMER_EXISTS);
    }

    private void checkNationalIdExist(String nationalId) throws BusinessException {
        if (Objects.nonNull(repository.findByNationalId(nationalId)))
            throw new BusinessException(MessageStrings.CUSTOMERS_NATIONAL_ID_EXISTS);
    }
}
