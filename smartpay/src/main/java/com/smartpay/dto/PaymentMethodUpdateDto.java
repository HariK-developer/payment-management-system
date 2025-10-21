package com.smartpay.dto;

import com.smartpay.entity.Enums.PaymentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentMethodUpdateDto {
    @NotBlank(message = "Payment method name is required")
    private String paymentMethodName;

    @NotNull(message = "Payment type is required")
    private PaymentType paymentType;
}
