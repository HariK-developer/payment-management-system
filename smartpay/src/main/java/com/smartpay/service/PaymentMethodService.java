package com.smartpay.service;

import com.smartpay.dto.PaymentMethodUpdateDto;
import com.smartpay.entity.PaymentMethods;
import com.smartpay.repository.PaymentMethodRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodService (PaymentMethodRepository paymentMethodRepository){
        this.paymentMethodRepository = paymentMethodRepository;

    }

    public Optional<PaymentMethods> get(UUID id){
        return paymentMethodRepository.findById(id);
    }

    public List<PaymentMethods> getAll(){
        return paymentMethodRepository.findAll();
    }

    public PaymentMethods create (PaymentMethods paymentMethod){
        if (paymentMethodRepository.existsByPaymentMethodName(paymentMethod.getPaymentMethodName())){
            throw new RuntimeException("Payment method name already existed");
        }
        return paymentMethodRepository.save(paymentMethod);
    }

    public PaymentMethods update (UUID id, PaymentMethodUpdateDto paymentMethod){
        PaymentMethods existingPaymentMethod  = paymentMethodRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment method not found with id"));
        BeanUtils.copyProperties(paymentMethod, existingPaymentMethod, "id");
        return paymentMethodRepository.save(existingPaymentMethod);
    }
}
