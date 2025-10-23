package com.smartpay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean status;
    private String message;
    private Optional<T> result = Optional.empty();

    public static <T>ApiResponse<T> success(String message, T result){
        return new ApiResponse<>(true, message, Optional.ofNullable(result));

    }

    public static  <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(true, message,Optional.empty());
    }

    public static <T> ApiResponse<T> failure(String message, T result){
        return new ApiResponse<>(false, message,Optional.ofNullable(result));
    }

    public static <T> ApiResponse<T> failure(String message){
        return new ApiResponse<>(false,message,Optional.empty());
    }
}