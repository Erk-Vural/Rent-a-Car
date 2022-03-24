package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.constant.MessageStrings;
import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.core.utils.results.SuccessResult;
import com.erkvural.rentacar.dto.car.create.BrandCreateRequest;
import com.erkvural.rentacar.dto.car.get.BrandGetResponse;
import com.erkvural.rentacar.dto.car.update.BrandUpdateRequest;
import com.erkvural.rentacar.entity.car.Brand;
import com.erkvural.rentacar.repository.car.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository repository;
    private final ModelMapperService modelMapperService;

    @Autowired
    public BrandServiceImpl(BrandRepository repository, ModelMapperService modelMapperService) {
        this.repository = repository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(BrandCreateRequest createRequest) throws BusinessException {
        checkBrandNameExist(createRequest.getName());

        Brand brand = this.modelMapperService.forRequest().map(createRequest, Brand.class);

        this.repository.save(brand);

        return new SuccessResult(MessageStrings.BRANDADD);
    }

    @Override
    public DataResult<List<BrandGetResponse>> getAll() {
        List<Brand> result = repository.findAll();

        List<BrandGetResponse> response = result.stream()
                .map(brand -> modelMapperService.forResponse()
                        .map(brand, BrandGetResponse.class)).toList();

        return new SuccessDataResult<>(MessageStrings.BRANDLIST, response);
    }

    @Override
    public DataResult<BrandGetResponse> getById(long id) throws BusinessException {
        checkBrandIdExist(id);

        Brand brand = repository.getById(id);
        BrandGetResponse response = modelMapperService.forResponse().map(brand, BrandGetResponse.class);

        return new SuccessDataResult<>(MessageStrings.BRANDFOUND, response);
    }

    @Override
    public Result update(long id, BrandUpdateRequest updateRequest) throws BusinessException {
        checkBrandIdExist(id);
        checkBrandNameExist(updateRequest.getName());

        Brand brand = this.modelMapperService.forRequest().map(updateRequest, Brand.class);
        brand.setId(id);

        this.repository.save(brand);

        return new SuccessResult(MessageStrings.BRANDUPDATE);
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkBrandIdExist(id);

        this.repository.deleteById(id);

        return new SuccessResult(MessageStrings.BRANDDELETE);
    }

    private void checkBrandIdExist(long id) throws BusinessException {
        if (Objects.nonNull(repository.findById(id)))
            throw new BusinessException(MessageStrings.BRANDNOTFOUND);
    }

    private void checkBrandNameExist(String name) throws BusinessException {
        if (!Objects.nonNull(repository.findByName(name)))
            throw new BusinessException(MessageStrings.BRANDNAMEERROR);

    }
}
