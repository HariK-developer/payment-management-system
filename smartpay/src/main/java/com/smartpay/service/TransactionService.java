package com.smartpay.service;

import com.smartpay.dto.TransactionCreateDto;
import com.smartpay.entity.PaymentMethods;
import com.smartpay.entity.Transactions;
import com.smartpay.entity.User;
import com.smartpay.repository.PaymentMethodRepository;
import com.smartpay.repository.TransactionRepository;
import com.smartpay.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, PaymentMethodRepository paymentMethodRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.userRepository = userRepository;
    }

    public Optional<Transactions> get(UUID id) {
        return transactionRepository.findById(id);
    }

    public List<Transactions> getAll() {

        return transactionRepository.findAll();
    }

    public Transactions createTransaction(TransactionCreateDto transaction){
        UUID paymentMethodId = UUID.fromString(transaction.getPaymentMethodId());
        Long userId = transaction.getUserId();
        BigDecimal amount = BigDecimal.valueOf(Long.parseLong(transaction.getAmount()));

        PaymentMethods method = paymentMethodRepository.findById(paymentMethodId).orElseThrow(() -> new RuntimeException("Payment method not found"));
        User user  = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User detail not found"));

        String lastCode = transactionRepository.findLastCodeByPaymentMethod(method.getId());
        int nextNumber = 1;

        if(lastCode != null){
            String numericPart = lastCode.replaceAll("\\D+","");
            if (!numericPart.isEmpty()){
                nextNumber = Integer.parseInt(numericPart)+ 1;
            }
        }

        String newCode = String.format("%s%o4d",method.getPaymentMethodName().toUpperCase(),nextNumber);

        Transactions t = new Transactions();
        t.setPaymentMethod(method);
        t.setUser(user);
        t.setAmount(amount);
        t.setReferenceCode(newCode);

        return transactionRepository.save(t);
    }
}
