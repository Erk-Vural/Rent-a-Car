package com.erkvural.rentacar.repository.car;

import com.erkvural.rentacar.entity.car.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Invoice findById(long id);

    List<Invoice> findAllByRentStartDateLessThanEqualAndRentEndDateGreaterThanEqual(LocalDate rentStartDate, LocalDate rentEndDate);

    List<Invoice> findByCustomer_UserId(long customerId);
}
