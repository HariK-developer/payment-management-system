package com.smartpay.controller;


import com.smartpay.dto.ApiResponse;
import com.smartpay.dto.TransactionCreateDto;
import com.smartpay.entity.Transactions;
import com.smartpay.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }


    @GetMapping("/id")
    public ApiResponse<Optional<Transactions>> getTransactionById(@PathVariable UUID id){
        Optional<Transactions> transaction = transactionService.get(id);
        return ApiResponse.success("Transaction not found",transaction);
    }

    @GetMapping
    public ApiResponse<List<Transactions>> getAll(){
        List<Transactions> transactions = transactionService.getAll();
         return ApiResponse.success("Transactions fetched successfully",transactions);
    }

    @PostMapping
    public ApiResponse<Transactions> create(@Valid @RequestBody TransactionCreateDto  transactionRequest){
        Transactions savedTransaction = transactionService.createTransaction(transactionRequest);

        return ApiResponse.success("Transactions created successfully");


    }
}
