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

    private final IndividualCustomerRepository individualCustomerRepository;
    private final ModelMapperService modelMapperService;

    @Autowired
    public IndividualCustomerServiceImpl(IndividualCustomerRepository individualCustomerRepository, ModelMapperService modelMapperService) {
        this.individualCustomerRepository = individualCustomerRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(IndividualCustomerCreateRequest individualCustomerCreateDto) throws BusinessException {
        checkEmailExist(individualCustomerCreateDto.getEmail());

        IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(individualCustomerCreateDto, IndividualCustomer.class);
        this.individualCustomerRepository.save(individualCustomer);

        return new SuccessResult(MessageStrings.CUSTOMERADD);
    }

    @Override
    public DataResult<List<IndividualCustomerGetResponse>> getAll() {
        List<IndividualCustomer> result = individualCustomerRepository.findAll();

        List<IndividualCustomerGetResponse> response = result.stream()
                .map(individualCustomer -> modelMapperService.forDto()
                        .map(individualCustomer, IndividualCustomerGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.CUSTOMERLIST, response);
    }

    @Override
    public DataResult<IndividualCustomerGetResponse> getById(long id) throws BusinessException {
        checkUserIdExist(id);

        IndividualCustomer individualCustomer = individualCustomerRepository.getById(id);
        IndividualCustomerGetResponse response = modelMapperService.forDto().map(individualCustomer, IndividualCustomerGetResponse.class);

        return new SuccessDataResult<>(MessageStrings.CUSTOMERGET, response);
    }

    @Override
    public Result update(long id, IndividualCustomerUpdateRequest individualCustomerUpdateDto) throws BusinessException {
        checkUserIdExist(id);
        checkEmailExist(individualCustomerUpdateDto.getEmail());

        IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(individualCustomerUpdateDto, IndividualCustomer.class);
        this.individualCustomerRepository.save(individualCustomer);

        return new SuccessResult(MessageStrings.CUSTOMERUPDATE);
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkUserIdExist(id);

        this.individualCustomerRepository.deleteById(id);

        return new SuccessResult(MessageStrings.CUSTOMERDELETE);
    }

    private void checkUserIdExist(long userId) throws BusinessException {
        if (Objects.nonNull(individualCustomerRepository.findByUserId(userId)))
            throw new BusinessException(MessageStrings.CUSTOMERNOTFOUND);
    }

    private void checkEmailExist(String email) throws BusinessException {
        if (Objects.nonNull(individualCustomerRepository.findByEmail(email)))
            throw new BusinessException(MessageStrings.CUSTOMERISALREADYEXISTS);
    }
}
