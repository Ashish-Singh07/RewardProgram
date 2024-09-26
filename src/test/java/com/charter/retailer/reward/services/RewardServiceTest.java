// RewardServiceTest.java
package com.charter.retailer.reward.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.charter.retailer.reward.dto.TransactionDTO;

@ExtendWith(MockitoExtension.class)
public class RewardServiceTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private RewardService rewardService;

    @Test
    void testGetRewardsBetweenDate() {
        // Arrange
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 2, 2);
        List<TransactionDTO> transactions = new ArrayList<>();
        transactions.add(new TransactionDTO(1L, "transaction1", 100.0, LocalDate.of(2024, 1, 1)));
        transactions.add(new TransactionDTO(2L, "transaction2", 200.0, LocalDate.of(2024, 2, 2)));
        when(transactionService.getTransactionBetweenDate(startDate, endDate)).thenReturn(transactions);

        // Act
        Double rewards = rewardService.getRewardsBetweenDate(startDate, endDate);

        // Assert
        assertEquals(650.0, rewards);
    }

    @Test
    void testGetTotalReward() {
        // Arrange
        List<TransactionDTO> transactions = new ArrayList<>();
        transactions.add(new TransactionDTO(1L, "transaction1", 100.0, LocalDate.of(2024, 1, 1)));
        transactions.add(new TransactionDTO(2L, "transaction2", 200.0, LocalDate.of(2024, 2, 2)));
        when(transactionService.getAllTransactions()).thenReturn(transactions);

        // Act
        Double rewards = rewardService.getTotalReward();

        // Assert
        assertEquals(650.0, rewards);
    }

    @Test
    void testCalculateReward() {
        // Arrange
        List<TransactionDTO> transactions = new ArrayList<>();
        transactions.add(new TransactionDTO(1L, "transaction1", 100.0, LocalDate.of(2024, 1, 1)));
        transactions.add(new TransactionDTO(2L, "transaction2", 200.0, LocalDate.of(2024, 2, 2)));

        // Act and Assert
        Double rewards = rewardService.calculateReward(transactions);
        assertEquals(650.0, rewards);
    }

    @Test
    void testCalculateRewardEmptyTransactions() {
        // Arrange
        List<TransactionDTO> transactions = new ArrayList<>();

        // Act and Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> rewardService.calculateReward(transactions));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }
}
