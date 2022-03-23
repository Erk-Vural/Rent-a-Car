package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.constant.MessageStrings;
import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.core.utils.results.SuccessResult;
import com.erkvural.rentacar.dto.car.create.CardInfoCreateRequest;
import com.erkvural.rentacar.dto.car.create.PaymentCreateRequest;
import com.erkvural.rentacar.dto.car.get.PaymentGetResponse;
import com.erkvural.rentacar.entity.car.Payment;
import com.erkvural.rentacar.repository.car.CarRentalRepository;
import com.erkvural.rentacar.repository.car.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repository;
    private final ModelMapperService modelMapperService;
    private final CarRentalRepository carRentalRepository;
    private final CardInfoService cardInfoService;

    @Autowired
    public PaymentServiceImpl(PaymentRepository repository, ModelMapperService modelMapperService, CarRentalRepository carRentalRepository, CardInfoService cardInfoService) {
        this.repository = repository;
        this.modelMapperService = modelMapperService;
        this.carRentalRepository = carRentalRepository;
        this.cardInfoService = cardInfoService;
    }

    @Override
    public Result add(PaymentCreateRequest createRequest, boolean rememberCardInfo) throws BusinessException {
        checkCarRentalIdExist(createRequest.getCarRentalId());
        // checkCardInfoIdExist(createRequest.getCardInfo());

        saveCardInfo(createRequest.getCardInfo(), rememberCardInfo);

        Payment payment = this.modelMapperService.forRequest().map(createRequest, Payment.class);

        this.repository.save(payment);

        return new SuccessResult(MessageStrings.PAYMENTADD);
    }

    @Override
    public DataResult<List<PaymentGetResponse>> getAll() {
        List<Payment> result = repository.findAll();

        List<PaymentGetResponse> response = result.stream()
                .map(payment -> modelMapperService.forResponse()
                        .map(payment, PaymentGetResponse.class)).toList();

        return new SuccessDataResult<>(MessageStrings.PAYMENTLIST, response);
    }

    @Override
    public DataResult<PaymentGetResponse> getById(long id) throws BusinessException {
        checkPaymentIdExist(id);

        Payment payment = repository.getById(id);
        PaymentGetResponse response = modelMapperService.forResponse().map(payment, PaymentGetResponse.class);

        return new SuccessDataResult<>(MessageStrings.PAYMENTFOUND, response);
    }

    private void checkPaymentIdExist(long id) throws BusinessException {
        if (Objects.nonNull(repository.findById(id)))
            throw new BusinessException(MessageStrings.PAYMENTNOTFOUND);
    }

    private void checkCarRentalIdExist(long carRentalId) throws BusinessException {
        if (Objects.nonNull(carRentalRepository.findById(carRentalId)))
            throw new BusinessException(MessageStrings.RENTALNOTFOUND);
    }

    private void saveCardInfo(CardInfoCreateRequest createRequest, boolean rememberCardInfo) throws BusinessException {
        if (rememberCardInfo) {
            this.cardInfoService.add(createRequest);
        }
    }
}
