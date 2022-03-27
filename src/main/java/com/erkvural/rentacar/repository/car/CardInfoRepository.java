package com.erkvural.rentacar.repository.car;

import com.erkvural.rentacar.entity.car.CardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardInfoRepository extends JpaRepository<CardInfo, Long> {

    CardInfo findById(long id);

    CardInfo findByCardholderNameAndCardNumberAndExpiryDateAndSecurityCode(String cardholderName, String cardNumber, String expiryDate, String securityCode);
}
