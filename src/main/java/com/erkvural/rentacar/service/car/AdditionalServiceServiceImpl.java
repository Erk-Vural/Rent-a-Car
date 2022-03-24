package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.constant.MessageStrings;
import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.core.utils.results.SuccessResult;
import com.erkvural.rentacar.dto.car.create.AdditionalServiceCreateRequest;
import com.erkvural.rentacar.dto.car.get.AdditionalServiceGetResponse;
import com.erkvural.rentacar.dto.car.update.AdditionalServiceUpdateRequest;
import com.erkvural.rentacar.entity.car.AdditionalService;
import com.erkvural.rentacar.repository.car.AdditionalServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AdditionalServiceServiceImpl implements AdditionalServiceService {

    private final AdditionalServiceRepository repository;
    private final ModelMapperService modelMapperService;

    @Autowired
    public AdditionalServiceServiceImpl(AdditionalServiceRepository repository, ModelMapperService modelMapperService) {
        this.repository = repository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(AdditionalServiceCreateRequest createRequest){
        checkAdditionalServiceExist(createRequest.getName());

        AdditionalService additionalService = this.modelMapperService.forRequest().map(createRequest, AdditionalService.class);
        this.repository.save(additionalService);

        return new SuccessResult(MessageStrings.ADDITIONAL_SERVICE_ADDED);
    }

    @Override
    public DataResult<List<AdditionalServiceGetResponse>> getAll() {
        List<AdditionalService> result = repository.findAll();
        List<AdditionalServiceGetResponse> response = result.stream()
                .map(additionalService -> modelMapperService.forResponse()
                        .map(additionalService, AdditionalServiceGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.ADDITIONAL_SERVICES_LISTED, response);
    }

    @Override
    public DataResult<AdditionalServiceGetResponse> getById(long id){
        checkAdditionalServiceIdExist(id);

        AdditionalService additionalService = repository.getById(id);
        AdditionalServiceGetResponse response = modelMapperService.forResponse().map(additionalService, AdditionalServiceGetResponse.class);

        return new SuccessDataResult<>(MessageStrings.ADDITIONAL_SERVICE_FOUND, response);
    }

    @Override
    public Result update(long id, AdditionalServiceUpdateRequest updateRequest){
        checkAdditionalServiceIdExist(id);

        AdditionalService additionalService = this.modelMapperService.forRequest().map(updateRequest, AdditionalService.class);
        additionalService.setId(id);

        this.repository.save(additionalService);

        return new SuccessResult(MessageStrings.ADDITIONAL_SERVICE_UPDATED);
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkAdditionalServiceIdExist(id);

        this.repository.deleteById(id);

        return new SuccessResult(MessageStrings.ADDITIONAL_SERVICE_DELETED);
    }

    private void checkAdditionalServiceIdExist(long id) throws BusinessException {
        if (Objects.nonNull(repository.findById(id)))
            throw new BusinessException(MessageStrings.ADDITIONAL_SERVICE_NOT_BY_ID_FOUND);
    }

    private void checkAdditionalServiceExist(String name) throws BusinessException {
        if (!Objects.nonNull(repository.findByName(name)))
            throw new BusinessException(MessageStrings.ADDITIONAL_SERVICE_EXISTS);
    }
}