package com.erkvural.rentacar.repository.car;

import com.erkvural.rentacar.entity.car.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
