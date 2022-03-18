package com.erkvural.rentacar.service.car;

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
    public Result add(AdditionalServiceCreateRequest additionalServiceCreateDto) throws BusinessException {
        checkAdditionalServiceExist(additionalServiceCreateDto.getName());

        AdditionalService additionalService = this.modelMapperService.forRequest().map(additionalServiceCreateDto, AdditionalService.class);
        this.additionalServiceRepository.save(additionalService);

        return new SuccessResult("Success, Additional Service added: " + additionalService.getName());
    }

    @Override
    public DataResult<List<AdditionalServiceGetResponse>> getAll() {
        List<AdditionalService> result = additionalServiceRepository.findAll();
        List<AdditionalServiceGetResponse> response = result.stream()
                .map(additionalService -> modelMapperService.forDto()
                        .map(additionalService, AdditionalServiceGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>("Success, All Additional Services listed.", response);
    }

    @Override
    public DataResult<AdditionalServiceGetResponse> getById(long id) throws BusinessException {
        checkAdditionalServiceIdExist(id);

        AdditionalService additionalService = additionalServiceRepository.getById(id);
        AdditionalServiceGetResponse response = modelMapperService.forDto().map(additionalService, AdditionalServiceGetResponse.class);

        return new SuccessDataResult<>("Success, Additional Service with requested ID found.", response);
    }

    @Override
    public Result update(long id, AdditionalServiceUpdateRequest additionalServiceUpdateDto) throws BusinessException {
        checkAdditionalServiceIdExist(id);

        AdditionalService additionalService = this.modelMapperService.forRequest().map(additionalServiceUpdateDto, AdditionalService.class);
        this.additionalServiceRepository.save(additionalService);

        return new SuccessResult("Success, Additional Service updated: " + additionalService.getName());
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkAdditionalServiceIdExist(id);

        this.additionalServiceRepository.deleteById(id);

        return new SuccessResult("Success, Additional Service deleted with requested ID: " + id);
    }

    private void checkAdditionalServiceIdExist(long id) throws BusinessException {
        if (Objects.nonNull(additionalServiceRepository.findById(id)))
            throw new BusinessException("Can't find Additional Service with id: " + id);
    }

    private void checkAdditionalServiceExist(String name) throws BusinessException {
        if (!Objects.nonNull(additionalServiceRepository.findByName(name)))
            throw new BusinessException("Additional Service with same name exists: " + name);

    }
}
