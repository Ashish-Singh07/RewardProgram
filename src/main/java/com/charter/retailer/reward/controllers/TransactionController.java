package com.charter.retailer.reward.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.charter.retailer.reward.dto.TransactionDTO;
import com.charter.retailer.reward.services.TransactionService;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    
    @GetMapping("/allTransactions")
    public List<TransactionDTO> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/id/{id}")
    public TransactionDTO getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }
    
    @GetMapping("/betweenDate")
    public List<TransactionDTO> getTransactionsBetweenDate(@RequestParam("startDate") java.time.LocalDate startDate, @RequestParam("endDate") java.time.LocalDate endDate) {
        return transactionService.getTransactionBetweenDate(startDate, endDate);
    }

    @GetMapping("/fromDate")
    public List<TransactionDTO> getTransactionsFromDate(@RequestParam("startDate") java.time.LocalDate startDate) {
        java.time.LocalDate endDate = java.time.LocalDate.now();
        return transactionService.getTransactionBetweenDate(startDate, endDate);
    }

    @PostMapping("/createTransaction")
    public TransactionDTO createTransactions(@RequestBody TransactionDTO transactionDTO) {
        return transactionService.createTransaction(transactionDTO);
    }

    @PostMapping("/createAllTransactions")
    public List<TransactionDTO> createAllTransactions(@RequestBody List<TransactionDTO> transactionDTOs) {
        return transactionService.createAllTransactions(transactionDTOs);
    }
}

