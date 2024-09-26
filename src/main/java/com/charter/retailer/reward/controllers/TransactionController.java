package com.charter.retailer.reward.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long id) {
        return new ResponseEntity<>(transactionService.getTransactionById(id), HttpStatus.OK);
    }
    
    @GetMapping("/betweenDate")
    public ResponseEntity<List<TransactionDTO>> getTransactionsBetweenDate(@RequestParam("startDate") java.time.LocalDate startDate, @RequestParam("endDate") java.time.LocalDate endDate) {
        return new ResponseEntity<>(transactionService.getTransactionBetweenDate(startDate, endDate), HttpStatus.OK);
    }

    @GetMapping("/fromDate")
    public ResponseEntity<List<TransactionDTO>> getTransactionsFromDate(@RequestParam("startDate") java.time.LocalDate startDate) {
        java.time.LocalDate endDate = java.time.LocalDate.now();
        return new ResponseEntity<>(transactionService.getTransactionBetweenDate(startDate, endDate), HttpStatus.OK);
    }

    @PostMapping("/createTransaction")
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        return new ResponseEntity<>(transactionService.createTransaction(transactionDTO), HttpStatus.CREATED);
    }

    @PostMapping("/createAllTransactions")
    public ResponseEntity<List<TransactionDTO>> createAllTransactions(@RequestBody List<TransactionDTO> transactionDTOs) {
        return new ResponseEntity<>(transactionService.createAllTransactions(transactionDTOs), HttpStatus.CREATED);
    }
}

