package com.charter.retailer.reward.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    private Long id;
    private String transactionName;
    private Double transactionAmount;
    private java.time.LocalDate transactionDate; // Format: yyyy-MM-dd
}
