package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.constant.MessageStrings;
import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.core.utils.results.SuccessResult;
import com.erkvural.rentacar.dto.car.create.CardInfoCreateRequest;
import com.erkvural.rentacar.dto.car.create.InvoiceCreateRequest;
import com.erkvural.rentacar.dto.car.create.PaymentCreateRequest;
import com.erkvural.rentacar.dto.car.get.PaymentGetResponse;
import com.erkvural.rentacar.entity.car.CardInfo;
import com.erkvural.rentacar.entity.car.Payment;
import com.erkvural.rentacar.repository.car.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repository;
    private final ModelMapperService modelMapperService;
    private final CardInfoService cardInfoService;
    private final CarRentalService carRentalService;
    private final InvoiceService invoiceService;

    @Autowired
    public PaymentServiceImpl(PaymentRepository repository, ModelMapperService modelMapperService, CardInfoService cardInfoService, CarRentalService carRentalService,@Lazy InvoiceService invoiceService) {
        this.repository = repository;
        this.modelMapperService = modelMapperService;
        this.cardInfoService = cardInfoService;
        this.carRentalService = carRentalService;
        this.invoiceService = invoiceService;
    }

    @Override
    public Result add(PaymentCreateRequest createRequest, boolean rememberCardInfo){
        checkCarRentalIdExist(createRequest.getCarRentalId());

        Payment payment = this.modelMapperService.forRequest().map(createRequest, Payment.class);

        payment.setTotal(carRentalService.calRentedTotal(createRequest.getCarRentalId()));

        payment.setCardInfo(handleCardInfo(createRequest.getCardInfo(), rememberCardInfo));

        this.repository.save(payment);

        invoiceService.add(new InvoiceCreateRequest(payment.getId()));

        return new SuccessResult(MessageStrings.PAYMENT_ADDED);
    }

    @Override
    public DataResult<List<PaymentGetResponse>> getAll() {
        List<Payment> result = repository.findAll();

        List<PaymentGetResponse> response = result.stream()
                .map(payment -> modelMapperService.forResponse()
                        .map(payment, PaymentGetResponse.class)).toList();

        return new SuccessDataResult<>(MessageStrings.PAYMENTS_LISTED, response);
    }

    @Override
    public DataResult<PaymentGetResponse> getById(long id){
        checkPaymentIdExist(id);

        Payment payment = repository.getById(id);
        PaymentGetResponse response = modelMapperService.forResponse().map(payment, PaymentGetResponse.class);

        return new SuccessDataResult<>(MessageStrings.PAYMENT_FOUND, response);
    }

    private void checkPaymentIdExist(long id) throws BusinessException {
        if (!Objects.nonNull(repository.findById(id)))
            throw new BusinessException(MessageStrings.PAYMENT_NOT_FOUND);
    }

    private void checkCarRentalIdExist(long carRentalId) throws BusinessException {
        if (!Objects.nonNull(carRentalService.getById(carRentalId).getData()))
            throw new BusinessException(MessageStrings.RENTAL_NOT_FOUND);
    }

    private CardInfo handleCardInfo(CardInfoCreateRequest createRequest, boolean rememberCardInfo) throws BusinessException {
        if (rememberCardInfo) {
            return saveCardInfo(createRequest);
        }
        return setCardInfo(createRequest);
    }

    private CardInfo saveCardInfo(CardInfoCreateRequest createRequest) throws BusinessException {
        return this.cardInfoService.addByPayment(createRequest).getData();
    }

    private CardInfo setCardInfo(CardInfoCreateRequest createRequest) {
        CardInfo cardInfo = new CardInfo();
        cardInfo.setCardNumber(createRequest.getCardNumber());
        cardInfo.setCardholderName(createRequest.getCardholderName());
        cardInfo.setExpiryDate(createRequest.getExpiryDate());
        cardInfo.setSecurityCode(createRequest.getSecurityCode());

        return cardInfo;
    }
}
