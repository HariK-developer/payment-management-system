package com.smartpay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean status;
    private String message;
    private T result;

    public static  <T> ApiResponse<T> success(String message, T result) {
        return new ApiResponse<>(true, message,result);
    }

    public static <T> ApiResponse<T> failure(String message, T result){
        return new ApiResponse<>(false, message,result);
    }
}