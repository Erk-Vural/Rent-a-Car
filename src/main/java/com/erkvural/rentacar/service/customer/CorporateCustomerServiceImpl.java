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
    public Result add(CorporateCustomerCreateRequest corporateCustomerCreateDto) throws BusinessException {
        checkEmailExist(corporateCustomerCreateDto.getEmail());

        CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(corporateCustomerCreateDto, CorporateCustomer.class);
        this.corporateCustomerRepository.save(corporateCustomer);

        return new SuccessResult(MessageStrings.CUSTOMERADD);
    }

    @Override
    public DataResult<List<CorporateCustomerGetResponse>> getAll() {
        List<CorporateCustomer> result = corporateCustomerRepository.findAll();

        List<CorporateCustomerGetResponse> response = result.stream()
                .map(corporateCustomer -> modelMapperService.forDto()
                        .map(corporateCustomer, CorporateCustomerGetResponse.class)).toList();

        return new SuccessDataResult<>(MessageStrings.CUSTOMERLIST, response);
    }

    @Override
    public DataResult<CorporateCustomerGetResponse> getById(long id) throws BusinessException {
        checkUserIdExist(id);

        CorporateCustomer corporateCustomer = corporateCustomerRepository.getById(id);
        CorporateCustomerGetResponse response = modelMapperService.forDto().map(corporateCustomer, CorporateCustomerGetResponse.class);

        return new SuccessDataResult<>(MessageStrings.CUSTOMERGET, response);
    }

    @Override
    public Result update(long id, CorporateCustomerUpdateRequest corporateCustomerUpdateRequest) throws BusinessException {
        checkUserIdExist(id);
        checkEmailExist(corporateCustomerUpdateRequest.getEmail());

        CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(corporateCustomerUpdateRequest, CorporateCustomer.class);
        this.corporateCustomerRepository.save(corporateCustomer);

        return new SuccessResult(MessageStrings.CUSTOMERUPDATE);
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkUserIdExist(id);

        this.corporateCustomerRepository.deleteById(id);

        return new SuccessResult(MessageStrings.CUSTOMERDELETE);
    }

    private void checkUserIdExist(long userId) throws BusinessException {
        if (Objects.nonNull(corporateCustomerRepository.findByUserId(userId)))
            throw new BusinessException(MessageStrings.CUSTOMERNOTFOUND);
    }

    private void checkEmailExist(String email) throws BusinessException {
        if (Objects.nonNull(corporateCustomerRepository.findByEmail(email)))
            throw new BusinessException(MessageStrings.CUSTOMERISALREADYEXISTS);
    }
}
