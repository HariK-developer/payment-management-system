package com.smartpay.controller;


import com.smartpay.dto.ApiResponse;
import com.smartpay.dto.PaymentMethodUpdateDto;
import com.smartpay.entity.PaymentMethods;
import com.smartpay.service.PaymentMethodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/payment-method")
public class PaymentMethodController {

    @Autowired
    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService){
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping("/{id}")
    public ApiResponse<Optional<PaymentMethods>> getPaymentMethodById(@PathVariable UUID id){
        Optional<PaymentMethods> paymentMethod = paymentMethodService.get(id);
        return ApiResponse.success("Payment method fetched successfully",paymentMethod);
    }

    @GetMapping
    public ApiResponse<List<PaymentMethods>> getAll(){
        List<PaymentMethods> paymentMethods = paymentMethodService.getAll();
        return ApiResponse.success("Payment methods fetched successfully",paymentMethods);
    }

    @PostMapping
    public ApiResponse<PaymentMethods> create(@RequestBody PaymentMethods paymentMethods){
        PaymentMethods savedPaymentMethod = paymentMethodService.create(paymentMethods);

        return ApiResponse.success("Payment method created successfully",savedPaymentMethod);
    }

    @PutMapping("/{id}")
    public ApiResponse<PaymentMethods> update (@PathVariable UUID id,@Valid @RequestBody PaymentMethodUpdateDto paymentMethod){
        if (paymentMethod == null) {
            return ApiResponse.failure("Request body is missing or empty", null);
        }
        PaymentMethods updatePaymentMethod = paymentMethodService.update(id,paymentMethod);
        return ApiResponse.success("Payment method updated successfully",updatePaymentMethod);
    }
}
