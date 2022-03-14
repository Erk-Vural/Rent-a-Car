package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.core.utils.results.SuccessResult;
import com.erkvural.rentacar.dto.car.create.BrandCreateDto;
import com.erkvural.rentacar.dto.car.get.BrandGetDto;
import com.erkvural.rentacar.dto.car.update.BrandUpdateDto;
import com.erkvural.rentacar.entity.car.Brand;
import com.erkvural.rentacar.repository.car.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapperService modelMapperService;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelMapperService modelMapperService) {
        this.brandRepository = brandRepository;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public Result add(BrandCreateDto brandCreateDto) throws BusinessException {
        checkBrandNameExist(brandCreateDto.getName());

        Brand brand = this.modelMapperService.forRequest().map(brandCreateDto, Brand.class);
        this.brandRepository.save(brand);

        return new SuccessResult("Success, Brand added: " + brand.getName());
    }

    @Override
    public DataResult<List<BrandGetDto>> getAll() {
        List<Brand> result = brandRepository.findAll();
        List<BrandGetDto> response = result.stream()
                .map(brand -> modelMapperService.forDto()
                        .map(brand, BrandGetDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>("Success, All Brands listed.", response);
    }

    @Override
    public DataResult<BrandGetDto> getById(long id) throws BusinessException {
        checkBrandIdExist(id);

        Brand brand = brandRepository.getById(id);
        BrandGetDto response = modelMapperService.forDto().map(brand, BrandGetDto.class);

        return new SuccessDataResult<>("Success, Brand with requested ID found.", response);
    }

    @Override
    public Result update(long id, BrandUpdateDto brandUpdateDto) throws BusinessException {
        checkBrandIdExist(id);

        Brand brand = this.modelMapperService.forRequest().map(brandUpdateDto, Brand.class);
        this.brandRepository.save(brand);

        return new SuccessResult("Success, Brand updated: " + brand.getName());
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkBrandIdExist(id);

        this.brandRepository.deleteById(id);

        return new SuccessResult("Success, Brand deleted with requested ID: " + id);
    }

    private void checkBrandIdExist(long id) throws BusinessException {
        if (Objects.nonNull(brandRepository.findById(id)))
            throw new BusinessException("Can't find Brand with id: " + id);
    }

    private void checkBrandNameExist(String name) throws BusinessException {
        if (!Objects.nonNull(brandRepository.findByName(name)))
            throw new BusinessException("Brand with same name exists: " + name);

    }
}
