package com.erkvural.rentacar.service.customer;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.core.utils.results.SuccessResult;
import com.erkvural.rentacar.dto.customer.create.CorporateCustomerCreateDto;
import com.erkvural.rentacar.dto.customer.get.CorporateCustomerGetDto;
import com.erkvural.rentacar.dto.customer.update.CorporateCustomerUpdateDto;
import com.erkvural.rentacar.entity.customer.CorporateCustomer;
import com.erkvural.rentacar.repository.customer.CorporateCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CorporateCustomerServiceImpl implements CorporateCustomerService {
    private final CorporateCustomerRepository corporateCustomerRepository;
    private final ModelMapperService modelMapperService;

    @Autowired
    public CorporateCustomerServiceImpl(CorporateCustomerRepository corporateCustomerRepository, ModelMapperService modelMapperService) {
        this.corporateCustomerRepository = corporateCustomerRepository;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public Result add(CorporateCustomerCreateDto corporateCustomerCreateDto) {

        CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(corporateCustomerCreateDto, CorporateCustomer.class);
        this.corporateCustomerRepository.save(corporateCustomer);

        return new SuccessResult("Success, Corporate Customer added: " + corporateCustomer.getEmail());
    }

    @Override
    public DataResult<List<CorporateCustomerGetDto>> getAll() {
        List<CorporateCustomer> result = corporateCustomerRepository.findAll();

        List<CorporateCustomerGetDto> response = result.stream()
                .map(corporateCustomer -> modelMapperService.forDto()
                        .map(corporateCustomer, CorporateCustomerGetDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>("Success, All Corporate Customer listed.", response);
    }

    @Override
    public DataResult<CorporateCustomerGetDto> getById(long id) throws BusinessException {
        checkUserIdExist(id);

        CorporateCustomer corporateCustomer = corporateCustomerRepository.getById(id);
        CorporateCustomerGetDto response = modelMapperService.forDto().map(corporateCustomer, CorporateCustomerGetDto.class);

        return new SuccessDataResult<>("Success, Corporate Customer with requested ID found.", response);
    }

    @Override
    public Result update(long id, CorporateCustomerUpdateDto corporateCustomerUpdateDto) throws BusinessException {
        checkUserIdExist(id);

        CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(corporateCustomerUpdateDto, CorporateCustomer.class);
        this.corporateCustomerRepository.save(corporateCustomer);

        return new SuccessResult("Success, Corporate Customer updated: " + corporateCustomer.getEmail());
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkUserIdExist(id);

        this.corporateCustomerRepository.deleteById(id);

        return new SuccessResult("Success, Corporate Customer deleted with requested ID: " + id);
    }

    private void checkUserIdExist(long userId) throws BusinessException {
        if (Objects.nonNull(corporateCustomerRepository.findByUserId(userId)))
            throw new BusinessException("Can't find Customer with userId: " + userId);
    }

}
