package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.constant.MessageStrings;
import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.core.utils.results.SuccessResult;
import com.erkvural.rentacar.dto.car.create.InvoiceCreateRequest;
import com.erkvural.rentacar.dto.car.get.CarRentalGetResponse;
import com.erkvural.rentacar.dto.car.get.InvoiceGetResponse;
import com.erkvural.rentacar.dto.car.update.BrandUpdateRequest;
import com.erkvural.rentacar.entity.car.Invoice;
import com.erkvural.rentacar.entity.car.Payment;
import com.erkvural.rentacar.repository.car.InvoiceRepository;
import com.erkvural.rentacar.repository.car.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final PaymentRepository paymentRepository;
    private final ModelMapperService modelMapperService;
    private final CarRentalService carRentalService;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, PaymentRepository paymentRepository, ModelMapperService modelMapperService, CarRentalService carRentalService) {
        this.invoiceRepository = invoiceRepository;
        this.paymentRepository = paymentRepository;
        this.modelMapperService = modelMapperService;
        this.carRentalService = carRentalService;
    }

    @Override
    public Result add(InvoiceCreateRequest createRequest) throws BusinessException {
        checkPaymentIdExist(createRequest.getPaymentId());

        Invoice invoice = setInvoice(createRequest.getPaymentId());

        this.invoiceRepository.save(invoice);

        return new SuccessResult(MessageStrings.INVOICEADD);
    }

    @Override
    public DataResult<List<InvoiceGetResponse>> getAll() {
        List<Invoice> result = invoiceRepository.findAll();

        List<InvoiceGetResponse> response = result.stream().map(invoice -> modelMapperService.forDto().map(invoice, InvoiceGetResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.INVOICELIST, response);
    }

    @Override
    public DataResult<InvoiceGetResponse> getById(long id) throws BusinessException {
        checkInvoiceIdExist(id);

        Invoice invoice = invoiceRepository.findById(id);
        InvoiceGetResponse response = modelMapperService.forDto().map(invoice, InvoiceGetResponse.class);

        return new SuccessDataResult<>(MessageStrings.INVOICEFOUND, response);
    }

    @Override
    public DataResult<List<InvoiceGetResponse>> getByCustomerId(long customerId) {
        List<Invoice> result = this.invoiceRepository.findByCustomer_UserId(customerId);

        List<InvoiceGetResponse> response = result.stream().map(invoice -> this.modelMapperService.forDto().map(invoice, InvoiceGetResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(MessageStrings.INVOICEBYCUSTOMERLIST, response);
    }

    @Override
    public DataResult<List<InvoiceGetResponse>> getByBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<Invoice> result = this.invoiceRepository.findAllByRentStartDateLessThanEqualAndRentEndDateGreaterThanEqual(startDate, endDate);

        List<InvoiceGetResponse> response = result.stream().map(invoice -> this.modelMapperService.forDto().map(invoice, InvoiceGetResponse.class)).toList();

        return new SuccessDataResult<>(MessageStrings.INVOICELIST, response);
    }

    @Override
    public Result update(long id, BrandUpdateRequest updateRequest) throws BusinessException {
        checkInvoiceIdExist(id);

        Invoice invoice = this.modelMapperService.forRequest().map(updateRequest, Invoice.class);
        invoice.setId(id);

        this.invoiceRepository.save(invoice);

        return new SuccessResult(MessageStrings.INVOICEUPDATE);
    }

    @Override
    public Result delete(long id) throws BusinessException {
        checkInvoiceIdExist(id);

        this.invoiceRepository.deleteById(id);

        return new SuccessResult(MessageStrings.INVOICEDELETE);
    }

    private void checkPaymentIdExist(long paymentId) throws BusinessException {
        if (Objects.nonNull(paymentRepository.findById(paymentId)))
            throw new BusinessException(MessageStrings.PAYMENTNOTFOUND);
    }

    private void checkInvoiceIdExist(long id) throws BusinessException {
        if (Objects.nonNull(invoiceRepository.findById(id)))
            throw new BusinessException(MessageStrings.INVOICEFOUND);
    }

    private Invoice setInvoice(long paymentId) throws BusinessException {

        Payment payment = this.paymentRepository.findById(paymentId);
        CarRentalGetResponse carRental = this.carRentalService.getById(payment.getCarRental().getId()).getData();

        Invoice invoice = new Invoice();

        invoice.setCreateDate(LocalDate.now());
        invoice.setRentStartDate(carRental.getStartDate());
        invoice.setRentEndDate(carRental.getEndDate());
        invoice.setTotalRentDays((ChronoUnit.DAYS.between(carRental.getStartDate(), carRental.getEndDate()) + 1));
        invoice.setTotal(payment.getTotal());

        return invoice;
    }
}
