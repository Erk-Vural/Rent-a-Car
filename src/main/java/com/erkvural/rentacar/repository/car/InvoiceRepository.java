package com.erkvural.rentacar.repository.car;

import com.erkvural.rentacar.entity.car.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Set;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Set<Invoice> findAllByRentStartDateLessThanEqualAndRentEndDateGreaterThanEqual(LocalDate rentStartDate, LocalDate rentEndDate);
}
