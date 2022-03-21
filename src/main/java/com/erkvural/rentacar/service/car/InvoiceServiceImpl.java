package com.erkvural.rentacar.service.car;

import com.erkvural.rentacar.core.exception.BusinessException;
import com.erkvural.rentacar.core.utils.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utils.results.DataResult;
import com.erkvural.rentacar.core.utils.results.Result;
import com.erkvural.rentacar.core.utils.results.SuccessDataResult;
import com.erkvural.rentacar.dto.car.create.CarRentalCreateRequest;
import com.erkvural.rentacar.dto.car.get.InvoiceGetResponse;
import com.erkvural.rentacar.dto.car.update.BrandUpdateRequest;
import com.erkvural.rentacar.entity.car.Invoice;
import com.erkvural.rentacar.repository.car.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ModelMapperService modelMapperService;
    private final CarRentalService carRentalService;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, ModelMapperService modelMapperService, CarRentalService carRentalService) {
        this.invoiceRepository = invoiceRepository;
        this.modelMapperService = modelMapperService;
        this.carRentalService = carRentalService;
    }

    @Override
    public Result add(CarRentalCreateRequest createRequest, long carRentalId) throws BusinessException {
        return null;
    }

    @Override
    public DataResult<List<InvoiceGetResponse>> getAll() {
        List<Invoice> result = invoiceRepository.findAll();

        List<InvoiceGetResponse> response = result.stream().map(invoice -> modelMapperService.forDto().map(invoice, InvoiceGetResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>("Success, All Invoices listed.", response);
    }

    @Override
    public DataResult<InvoiceGetResponse> getById(long id) throws BusinessException {
        checkInvoiceIdExist(id);

        Invoice invoice = invoiceRepository.findById(id);
        InvoiceGetResponse response = modelMapperService.forDto().map(invoice, InvoiceGetResponse.class);

        return new SuccessDataResult<>("Success, Invoice with requested ID found.", response);
    }

    @Override
    public DataResult<List<InvoiceGetResponse>> getByCustomerId(long customerId) {
        List<Invoice> result = this.invoiceRepository.findByCustomer_UserId(customerId);

        List<InvoiceGetResponse> response = result.stream().map(invoice -> this.modelMapperService.forDto().map(invoice, InvoiceGetResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>("Success, Invoices with requested customerID found", response);
    }

    @Override
    public DataResult<InvoiceGetResponse> getByBetweenDates(LocalDate endDate, LocalDate startDate) {
        return null;
    }

    @Override
    public Result update(long id, BrandUpdateRequest updateRequest) throws BusinessException {
        return null;
    }

    @Override
    public Result delete(long id) throws BusinessException {
        return null;
    }


    private void checkInvoiceIdExist(long id) throws BusinessException {
        if (Objects.nonNull(invoiceRepository.findById(id)))
            throw new BusinessException("Can't find Car Rental with id: " + id);
    }

}
