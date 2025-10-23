package com.smartpay.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class TransactionCreateDto {

    @NotNull(message = "Amount is required")
    @Pattern(
            regexp = "^[0-9]+(\\.[0-9]{1,2})?$",
            message = "Amount must be a valid number with up to 2 decimal places"
    )
    private String amount;

    @NotNull(message = "user detail is required")
    @Positive(message = "User ID must be a positive number")
    private Long userId;

    @NotNull(message = "payment method is required")
    @Pattern(
            regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$",
            message = "Payment method ID must be a valid UUID"
    )
    private String paymentMethodId;

}
