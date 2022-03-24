package com.erkvural.rentacar.service.customer;

import com.erkvural.rentacar.constant.MessageStrings;
import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.core.utils.results.SuccessResult;
import com.erkvural.rentacar.dto.customer.create.CorporateCustomerCreateRequest;
import com.erkvural.rentacar.dto.customer.get.CorporateCustomerGetResponse;
import com.erkvural.rentacar.dto.customer.update.CorporateCustomerUpdateRequest;
import com.erkvural.rentacar.entity.customer.CorporateCustomer;
import com.erkvural.rentacar.repository.customer.CorporateCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CorporateCustomerServiceImpl implements CorporateCustomerService {
    private final CorporateCustomerRepository repository;
    private final ModelMapperService modelMapperService;

    @Autowired
    public CorporateCustomerServiceImpl(CorporateCustomerRepository repository, ModelMapperService modelMapperService) {
        this.repository = repository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CorporateCustomerCreateRequest createRequest) throws BusinessException {
        checkEmailExist(createRequest.getEmail());

        CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(createRequest, CorporateCustomer.class);
        this.repository.save(corporateCustomer);

        return new SuccessResult(MessageStrings.CUSTOMER_ADDED);
    }

    @Override
    public DataResult<List<CorporateCustomerGetResponse>> getAll() {
        List<CorporateCustomer> result = repository.findAll();

        List<CorporateCustomerGetResponse> response = result.stream()
                .map(corporateCustomer -> modelMapperService.forResponse()
                        .map(corporateCustomer, CorporateCustomerGetResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.CUSTOMERS_LISTED, response);
    }

    @Override
    public DataResult<CorporateCustomerGetResponse> getById(long id) throws BusinessException {
        checkUserIdExist(id);

        CorporateCustomer corporateCustomer = repository.getById(id);
        CorporateCustomerGetResponse response = modelMapperService.forResponse().map(corporateCustomer, CorporateCustomerGetResponse.class);

        return new SuccessDataResult<>(MessageStrings.CUSTOMER_FOUND, response);
    }

    @Override
    public Result update(long id, CorporateCustomerUpdateRequest updateRequest) throws BusinessException {
        checkUserIdExist(id);
        checkEmailExist(updateRequest.getEmail());

        CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(updateRequest, CorporateCustomer.class);
        corporateCustomer.setUserId(id);

        this.repository.save(corporateCustomer);

        return new SuccessResult(MessageStrings.CUSTOMER_UPDATED);
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkUserIdExist(id);

        this.repository.deleteById(id);

        return new SuccessResult(MessageStrings.CUSTOMER_DELETED);
    }

    private void checkUserIdExist(long userId) throws BusinessException {
        if (Objects.nonNull(repository.findByUserId(userId)))
            throw new BusinessException(MessageStrings.CUSTOMER_NOT_FOUND);
    }

    private void checkEmailExist(String email) throws BusinessException {
        if (Objects.nonNull(repository.findByEmail(email)))
            throw new BusinessException(MessageStrings.CUSTOMER_EXISTS);
    }
}
