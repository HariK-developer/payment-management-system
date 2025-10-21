package com.smartpay.repository;

import com.smartpay.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transactions, UUID> {

    @Query(value = "SELECT referenceCode FROM transactions  where payment_method_id = :paymentMethodId ORDER BY createdAt DESC LIMIT 1",nativeQuery = true)
    String findLastCodeByPaymentMethod(@Param("paymentMethodId") UUID paymentMethodId);
}
