package com.charter.retailer.reward.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionDTO {

    private Long id;
    private String transactionName;
    private Double transactionAmount;
    private java.time.LocalDate transactionDate; // Format: yyyy-MM-dd
}
