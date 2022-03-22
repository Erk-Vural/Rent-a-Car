package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.constant.MessageStrings;
import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.core.utils.results.SuccessResult;
import com.erkvural.rentacar.dto.car.create.CardInfoCreateRequest;
import com.erkvural.rentacar.dto.car.get.CardInfoGetResponse;
import com.erkvural.rentacar.dto.car.update.CardInfoUpdateRequest;
import com.erkvural.rentacar.entity.car.CardInfo;
import com.erkvural.rentacar.repository.car.CardInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CardInfoServiceImpl implements CardInfoService {
    private final CardInfoRepository cardInfoRepository;
    private final ModelMapperService modelMapperService;

    @Autowired
    public CardInfoServiceImpl(CardInfoRepository cardInfoRepository, ModelMapperService modelMapperService) {
        this.cardInfoRepository = cardInfoRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CardInfoCreateRequest createRequest) throws BusinessException {

        CardInfo cardInfo = this.modelMapperService.forRequest().map(createRequest, CardInfo.class);

        this.cardInfoRepository.save(cardInfo);

        return new SuccessResult(MessageStrings.CREDITCARDADD);
    }

    @Override
    public DataResult<List<CardInfoGetResponse>> getAll() {
        List<CardInfo> result = cardInfoRepository.findAll();

        List<CardInfoGetResponse> response = result.stream()
                .map(cardInfo -> modelMapperService.forDto()
                        .map(cardInfo, CardInfoGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.CREDITCARDLIST, response);
    }

    @Override
    public DataResult<CardInfoGetResponse> getById(long id) throws BusinessException {
        checkCardInfoIdExist(id);

        CardInfo cardInfo = cardInfoRepository.getById(id);
        CardInfoGetResponse response = modelMapperService.forDto().map(cardInfo, CardInfoGetResponse.class);

        return new SuccessDataResult<>(MessageStrings.CREDITCARDGET, response);
    }

    @Override
    public Result update(long id, CardInfoUpdateRequest updateRequest) throws BusinessException {
        checkCardInfoIdExist(id);

        CardInfo cardInfo = this.modelMapperService.forRequest().map(updateRequest, CardInfo.class);
        cardInfo.setId(id);

        this.cardInfoRepository.save(cardInfo);

        return new SuccessResult(MessageStrings.CREDITCARDUPDATE);
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkCardInfoIdExist(id);

        this.cardInfoRepository.deleteById(id);

        return new SuccessResult(MessageStrings.CREDITCARDELETE);
    }

    private void checkCardInfoIdExist(long id) throws BusinessException {
        if (Objects.nonNull(cardInfoRepository.findById(id)))
            throw new BusinessException(MessageStrings.CREDITCARDNOTFOUND);
    }
}
