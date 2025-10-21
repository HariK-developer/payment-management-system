package com.smartpay.repository;

import com.smartpay.entity.PaymentMethods;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethods, UUID> {
    boolean  existsByPaymentMethodName(String paymentMethodName);

}
