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
import com.erkvural.rentacar.entity.car.Payment;
import com.erkvural.rentacar.entity.customer.Customer;
import com.erkvural.rentacar.pos.service.PosService;
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
    private final PosService posService;

    @Autowired
    public PaymentServiceImpl(PaymentRepository repository, ModelMapperService modelMapperService, CardInfoService cardInfoService, CarRentalService carRentalService, @Lazy InvoiceService invoiceService, PosService posService) {
        this.repository = repository;
        this.modelMapperService = modelMapperService;
        this.cardInfoService = cardInfoService;
        this.carRentalService = carRentalService;
        this.invoiceService = invoiceService;
        this.posService = posService;
    }

    @Override
    public Result add(PaymentCreateRequest createRequest, boolean rememberCardInfo) {
        checkCarRentalIdExist(createRequest.getCarRentalId());

        Payment payment = this.modelMapperService.forRequest().map(createRequest, Payment.class);

        payment.setTotal(carRentalService.calRentedTotal(createRequest.getCarRentalId()));

        Customer customer = new Customer();
        customer.setUserId(carRentalService.getById(payment.getCarRental().getId()).getData().getCustomerId());
        payment.setCustomer(customer);

        saveCardInfo(createRequest.getCardInfo(), rememberCardInfo, carRentalService.getById(payment.getCarRental().getId()).getData().getCustomerId());

        payment = this.repository.saveAndFlush(payment);

        invoiceService.add(new InvoiceCreateRequest(payment.getId()));

        sendPosService(createRequest);

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
    public DataResult<PaymentGetResponse> getById(long id) {
        checkPaymentIdExist(id);

        Payment payment = repository.getById(id);
        PaymentGetResponse response = modelMapperService.forResponse().map(payment, PaymentGetResponse.class);

        return new SuccessDataResult<>(MessageStrings.PAYMENT_FOUND, response);
    }

    private void sendPosService(PaymentCreateRequest createRequest) {
        this.posService.addPayment(createRequest);
    }

    private void checkPaymentIdExist(long id) throws BusinessException {
        if (!Objects.nonNull(repository.findById(id)))
            throw new BusinessException(MessageStrings.PAYMENT_NOT_FOUND);
    }

    private void checkCarRentalIdExist(long carRentalId) throws BusinessException {
        if (!Objects.nonNull(carRentalService.getById(carRentalId).getData()))
            throw new BusinessException(MessageStrings.RENTAL_NOT_FOUND);
    }

    private void saveCardInfo(CardInfoCreateRequest createRequest, boolean rememberCardInfo, long customerId) throws BusinessException {
        if (rememberCardInfo) {
            this.cardInfoService.add(createRequest, customerId);
        }
    }
}
