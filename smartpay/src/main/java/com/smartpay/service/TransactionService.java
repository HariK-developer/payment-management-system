package com.smartpay.service;


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

    public Transactions createTransaction(Transactions transaction ) {
        PaymentMethods method = transaction.getPaymentMethod();

        String lastCode = transactionRepository.findLastCodeByPaymentMethod(method.getId());
        int nextNumber = 1;
        if (lastcode != null){
            String numericPart = lastCode.replaceAll("\\D+","");
            nextNumber = Integer.parseInt(numericPart) + 1;
        }

        String newCode = String.format("%s%04d", method.getPaymentMethodName().toUpperCase(), nextNumber);
        transaction.setReferenceCode(newCode);
        User user = userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("Payment method not found"));
        Transactions t = new Transactions();
        t.setPaymentMethod(method);
        t.setUser(user);
        t.setAmount(BigDecimal.valueOf(200));
        return transactionRepository.save(t);
    }
}
