package com.erkvural.rentacar.repository.car;

import com.erkvural.rentacar.entity.car.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Set<Invoice> findAllByRentStartDateLessThanEqualAndRentEndDateGreaterThanEqual(LocalDate rentStartDate, LocalDate rentEndDate);
}
