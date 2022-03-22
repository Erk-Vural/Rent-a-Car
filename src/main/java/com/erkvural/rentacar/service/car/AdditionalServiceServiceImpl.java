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

    private final AdditionalServiceRepository additionalServiceRepository;
    private final ModelMapperService modelMapperService;

    @Autowired
    public AdditionalServiceServiceImpl(AdditionalServiceRepository additionalServiceRepository, ModelMapperService modelMapperService) {
        this.additionalServiceRepository = additionalServiceRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(AdditionalServiceCreateRequest createRequest) throws BusinessException {
        checkAdditionalServiceExist(createRequest.getName());

        AdditionalService additionalService = this.modelMapperService.forRequest().map(createRequest, AdditionalService.class);
        this.additionalServiceRepository.save(additionalService);

        return new SuccessResult(MessageStrings.ADDITIONALSERVICEADD);
    }

    @Override
    public DataResult<List<AdditionalServiceGetResponse>> getAll() {
        List<AdditionalService> result = additionalServiceRepository.findAll();
        List<AdditionalServiceGetResponse> response = result.stream()
                .map(additionalService -> modelMapperService.forDto()
                        .map(additionalService, AdditionalServiceGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.ADDITIONALSERVICELIST, response);
    }

    @Override
    public DataResult<AdditionalServiceGetResponse> getById(long id) throws BusinessException {
        checkAdditionalServiceIdExist(id);

        AdditionalService additionalService = additionalServiceRepository.getById(id);
        AdditionalServiceGetResponse response = modelMapperService.forDto().map(additionalService, AdditionalServiceGetResponse.class);

        return new SuccessDataResult<>(MessageStrings.ADDITIONALSERVICEGET, response);
    }

    @Override
    public Result update(long id, AdditionalServiceUpdateRequest updateRequest) throws BusinessException {
        checkAdditionalServiceIdExist(id);

        AdditionalService additionalService = this.modelMapperService.forRequest().map(updateRequest, AdditionalService.class);
        additionalService.setId(id);

        this.additionalServiceRepository.save(additionalService);

        return new SuccessResult(MessageStrings.ADDITIONALSERVICEUPDATE);
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkAdditionalServiceIdExist(id);

        this.additionalServiceRepository.deleteById(id);

        return new SuccessResult(MessageStrings.ADDITIONALSERVICEDELETE);
    }

    private void checkAdditionalServiceIdExist(long id) throws BusinessException {
        if (Objects.nonNull(additionalServiceRepository.findById(id)))
            throw new BusinessException(MessageStrings.ADDITIONALSERVICENOTIDFOUND);
    }

    private void checkAdditionalServiceExist(String name) throws BusinessException {
        if (!Objects.nonNull(additionalServiceRepository.findByName(name)))
            throw new BusinessException(MessageStrings.ADDITIONALSERVICENAMEERROR);

    }
}
