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
import com.erkvural.rentacar.entity.customer.Customer;
import com.erkvural.rentacar.repository.car.CardInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CardInfoServiceImpl implements CardInfoService {
    private final CardInfoRepository repository;
    private final ModelMapperService modelMapperService;

    @Autowired
    public CardInfoServiceImpl(CardInfoRepository cardInfoRepository, ModelMapperService modelMapperService) {
        this.repository = cardInfoRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CardInfoCreateRequest createRequest, long customerId) {
        checkCardNumber(createRequest.getCardNumber());
        checkExpiryDate(createRequest.getExpiryDate());
        checkSecurityCode(createRequest.getSecurityCode());
        checkCardExists(createRequest);

        CardInfo cardInfo = this.modelMapperService.forRequest().map(createRequest, CardInfo.class);

        Customer customer = new Customer();
        customer.setUserId(customerId);
        cardInfo.setCustomer(customer);

        this.repository.save(cardInfo);

        return new SuccessResult(MessageStrings.CREDIT_CARD_ADDED);
    }

    @Override
    public DataResult<List<CardInfoGetResponse>> getAll() {
        List<CardInfo> result = repository.findAll();

        List<CardInfoGetResponse> response = result.stream()
                .map(cardInfo -> modelMapperService.forResponse()
                        .map(cardInfo, CardInfoGetResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.CREDIT_CARDS_LISTED, response);
    }

    @Override
    public DataResult<CardInfoGetResponse> getById(long id) {
        checkCardInfoIdExist(id);

        CardInfo cardInfo = repository.getById(id);
        CardInfoGetResponse response = modelMapperService.forResponse().map(cardInfo, CardInfoGetResponse.class);

        return new SuccessDataResult<>(MessageStrings.CREDIT_CARD_FOUND, response);
    }

    @Override
    public Result update(long id, CardInfoUpdateRequest updateRequest) {
        checkCardInfoIdExist(id);
        checkCardNumber(updateRequest.getCardNumber());
        checkExpiryDate(updateRequest.getExpiryDate());
        checkSecurityCode(updateRequest.getSecurityCode());

        CardInfo cardInfo = this.modelMapperService.forRequest().map(updateRequest, CardInfo.class);
        cardInfo.setId(id);

        this.repository.save(cardInfo);

        return new SuccessResult(MessageStrings.CREDIT_CARD_UPDATED);
    }

    @Override
    public Result delete(long id) {
        checkCardInfoIdExist(id);

        this.repository.deleteById(id);

        return new SuccessResult(MessageStrings.CREDIT_CARD_DELETED);
    }

    private void checkCardInfoIdExist(long id) throws BusinessException {
        if (!Objects.nonNull(repository.findById(id)))
            throw new BusinessException(MessageStrings.CREDIT_CARD_NOT_FOUND);
    }

    private void checkCardNumber(String cardNumber) {
        if (cardNumber.length() != 16)
            throw new BusinessException(MessageStrings.CREDIT_CARD_NUMBER_ERROR);
    }

    private void checkExpiryDate(String expiryDate) {
        int month = Integer.parseInt(expiryDate.substring(0, 2));
        int year = Integer.parseInt(expiryDate.substring(expiryDate.length() - 2)) + 2000;

        if (month < LocalDate.now().getMonthValue() && year <= LocalDate.now().getYear()) {
            throw new BusinessException(MessageStrings.CREDIT_CARD_DATE_ERROR);
        }
    }

    private void checkSecurityCode(String securityCode) {
        if (securityCode.length() != 3)
            throw new BusinessException(MessageStrings.CREDIT_CARD_CVV_ERROR);
    }

    private void checkCardExists(CardInfoCreateRequest createRequest) {
        if (!Objects.nonNull(repository.findByCardholderNameAndCardNumberAndExpiryDateAndSecurityCode(createRequest.getCardholderName(), createRequest.getCardNumber(), createRequest.getExpiryDate(), createRequest.getSecurityCode())))
            throw new BusinessException(MessageStrings.CREDIT_CARD_EXISTS);
    }
}
