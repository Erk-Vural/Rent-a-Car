package com.erkvural.rentacar.service.customer;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.core.utils.results.SuccessResult;
import com.erkvural.rentacar.dto.customer.create.IndividualCustomerCreateDto;
import com.erkvural.rentacar.dto.customer.get.IndividualCustomerGetDto;
import com.erkvural.rentacar.dto.customer.update.IndividualCustomerUpdateDto;
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
    public Result add(IndividualCustomerCreateDto individualCustomerCreateDto) {

        IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(individualCustomerCreateDto, IndividualCustomer.class);
        this.individualCustomerRepository.save(individualCustomer);

        return new SuccessResult("Success, individual Customer added: " + individualCustomer.getEmail());
    }

    @Override
    public DataResult<List<IndividualCustomerGetDto>> getAll() {
        List<IndividualCustomer> result = individualCustomerRepository.findAll();

        List<IndividualCustomerGetDto> response = result.stream()
                .map(individualCustomer -> modelMapperService.forDto()
                        .map(individualCustomer, IndividualCustomerGetDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>("Success, All individual Customer listed.", response);
    }

    @Override
    public DataResult<IndividualCustomerGetDto> getById(long id) throws BusinessException {
        checkUserIdExist(id);

        IndividualCustomer individualCustomer = individualCustomerRepository.getById(id);
        IndividualCustomerGetDto response = modelMapperService.forDto().map(individualCustomer, IndividualCustomerGetDto.class);

        return new SuccessDataResult<>("Success, individual Customer with requested ID found.", response);
    }

    @Override
    public Result update(long id, IndividualCustomerUpdateDto individualCustomerUpdateDto) throws BusinessException {
        checkUserIdExist(id);

        IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(individualCustomerUpdateDto, IndividualCustomer.class);
        this.individualCustomerRepository.save(individualCustomer);

        return new SuccessResult("Success, individual Customer updated: " + individualCustomer.getEmail());
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkUserIdExist(id);

        this.individualCustomerRepository.deleteById(id);

        return new SuccessResult("Success, individual Customer deleted with requested ID: " + id);
    }

    private void checkUserIdExist(long userId) throws BusinessException {
        if (Objects.nonNull(individualCustomerRepository.findByUserId(userId)))
            throw new BusinessException("Can't find Customer with userId: " + userId);
    }
}
