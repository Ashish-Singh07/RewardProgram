// TransactionServiceTest.java
package com.charter.retailer.reward.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.charter.retailer.reward.dto.TransactionDTO;
import com.charter.retailer.reward.entities.TransactionEntity;
import com.charter.retailer.reward.repositories.TransactionRepository;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void testGetAllTransactions() {
        // Arrange
        List<TransactionEntity> transactionEntities = new ArrayList<>();
        transactionEntities.add(new TransactionEntity(1L, "transaction1", 100.0, LocalDate.parse("2024-01-01"))); 
        transactionEntities.add(new TransactionEntity(2L, "transaction2", 200.0, LocalDate.parse("2024-02-02")));
        transactionEntities.add(new TransactionEntity(3L, "transaction3", 300.0, LocalDate.parse("2024-03-03")));
        when(transactionRepository.findAll()).thenReturn(transactionEntities);

        TransactionDTO transactionDTO1 = new TransactionDTO(1L, "transaction1", 100.0, LocalDate.parse("2024-01-01"));
        TransactionDTO transactionDTO2 = new TransactionDTO(2L, "transaction2", 200.0, LocalDate.parse("2024-02-02"));
        TransactionDTO transactionDTO3 = new TransactionDTO(3L, "transaction3", 300.0, LocalDate.parse("2024-03-03"));
        when(modelMapper.map(transactionEntities.get(0), TransactionDTO.class)).thenReturn(transactionDTO1);
        when(modelMapper.map(transactionEntities.get(1), TransactionDTO.class)).thenReturn(transactionDTO2);
        when(modelMapper.map(transactionEntities.get(2), TransactionDTO.class)).thenReturn(transactionDTO3);

        // Act
        List<TransactionDTO> transactions = transactionService.getAllTransactions();

        // Assert
        assertEquals(3, transactions.size());
        assertEquals(transactionDTO1, transactions.get(0));
        assertEquals(transactionDTO2, transactions.get(1));
        assertEquals(transactionDTO3, transactions.get(2));
    }

    @Test
    void testGetTransactionById() {
        // Arrange
        Long id = 1L;
        TransactionEntity transactionEntity = new TransactionEntity(id, "transaction1", 100.0, LocalDate.parse("2024-02-15"));

        when(transactionRepository.findById(id)).thenReturn(Optional.of(transactionEntity));

        TransactionDTO transactionDTO = new TransactionDTO(id, "transaction1", 100.0, LocalDate.parse("2024-02-15"));
        when(modelMapper.map(transactionEntity, TransactionDTO.class)).thenReturn(transactionDTO);

        // Act
        TransactionDTO transaction = transactionService.getTransactionById(id);

        // Assert
        assertEquals(transactionDTO, transaction);
    }

    @Test
    void testGetTransactionByIdNotFound()  {
        // Arrange
        Long id = 1L;
        when(transactionRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> transactionService.getTransactionById(id));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void testGetTransactionBetweenDate() {
        // Arrange
        LocalDate startDate = LocalDate.parse("2024-01-01");
        LocalDate endDate = LocalDate.parse("2024-04-04");
        List<TransactionEntity> transactionEntities = new ArrayList<>();
        transactionEntities.add(new TransactionEntity(1L, "transaction1", 100.0, LocalDate.parse("2024-01-01"))); 
        transactionEntities.add(new TransactionEntity(2L, "transaction2", 200.0, LocalDate.parse("2024-02-02")));
        when(transactionRepository.findByTransactionDateBetween(startDate, endDate)).thenReturn(transactionEntities);

        TransactionDTO transactionDTO1 = new TransactionDTO(1L, "transaction1", 100.0, LocalDate.parse("2024-01-01"));
        TransactionDTO transactionDTO2 = new TransactionDTO(2L, "transaction2", 200.0, LocalDate.parse("2024-02-02"));
        when(modelMapper.map(transactionEntities.get(0), TransactionDTO.class)).thenReturn(transactionDTO1);
        when(modelMapper.map(transactionEntities.get(1), TransactionDTO.class)).thenReturn(transactionDTO2);

        // Act
        List<TransactionDTO> transactions = transactionService.getTransactionBetweenDate(startDate, endDate);

        // Assert
        assertEquals(2, transactions.size());
        assertEquals(transactionDTO1, transactions.get(0));
        assertEquals(transactionDTO2, transactions.get(1));
    }

    @Test
    void testCreateTransaction() {
        // Arrange
        TransactionDTO transactionDTO = new TransactionDTO(1L, "transaction1", 100.0, LocalDate.parse("2024-01-01"));
        TransactionEntity transactionEntity = new TransactionEntity(1L, "transaction1", 100.0, LocalDate.parse("2024-01-01"));
        when(modelMapper.map(transactionDTO, TransactionEntity.class)).thenReturn(transactionEntity);

        TransactionEntity savedTransactionEntity = new TransactionEntity(1L, "transaction1", 100.0, LocalDate.parse("2024-01-01"));
        when(transactionRepository.save(transactionEntity)).thenReturn(savedTransactionEntity);

        TransactionDTO savedTransactionDTO = new TransactionDTO(1L, "transaction1", 100.0, LocalDate.parse("2024-01-01"));
        when(modelMapper.map(savedTransactionEntity, TransactionDTO.class)).thenReturn(savedTransactionDTO);

        // Act
        TransactionDTO transaction = transactionService.createTransaction(transactionDTO);

        // Assert
        assertEquals(savedTransactionDTO, transaction);
    }

    @Test
    void testCreateAllTransactions() {
        // Arrange
        List<TransactionDTO> transactionDTOs = new ArrayList<>();
        transactionDTOs.add(new TransactionDTO(1L, "transaction1", 100.0, LocalDate.parse("2024-01-01")));
        transactionDTOs.add(new TransactionDTO(2L, "transaction2", 200.0, LocalDate.parse("2024-02-02")));

        List<TransactionEntity> transactionEntities = new ArrayList<>();
        transactionEntities.add(new TransactionEntity(1L, "transaction1", 100.0, LocalDate.parse("2024-01-01"))); 
        transactionEntities.add(new TransactionEntity(2L, "transaction2", 200.0, LocalDate.parse("2024-02-02")));
        when(modelMapper.map(transactionDTOs.get(0), TransactionEntity.class)).thenReturn(transactionEntities.get(0));
        when(modelMapper.map(transactionDTOs.get(1), TransactionEntity.class)).thenReturn(transactionEntities.get(1));

        List<TransactionEntity> savedTransactionEntities = new ArrayList<>();
        savedTransactionEntities.add(new TransactionEntity(1L, "transaction1", 100.0, LocalDate.parse("2024-01-01")));
        savedTransactionEntities.add(new TransactionEntity(2L, "transaction2", 200.0, LocalDate.parse("2024-02-02")));
        when(transactionRepository.saveAll(transactionEntities)).thenReturn(savedTransactionEntities);

        List<TransactionDTO> savedTransactionDTOs = new ArrayList<>();
        savedTransactionDTOs.add(new TransactionDTO(1L, "transaction1", 100.0, LocalDate.parse("2024-01-01")));
        savedTransactionDTOs.add(new TransactionDTO(2L, "transaction2", 200.0, LocalDate.parse("2024-02-02")));
        when(modelMapper.map(savedTransactionEntities.get(0), TransactionDTO.class)).thenReturn(savedTransactionDTOs.get(0));
        when(modelMapper.map(savedTransactionEntities.get(1), TransactionDTO.class)).thenReturn(savedTransactionDTOs.get(1));

        // Act
        List<TransactionDTO> transactions = transactionService.createAllTransactions(transactionDTOs);

        // Assert
        assertEquals(2, transactions.size());
        assertEquals(savedTransactionDTOs.get(0), transactions.get(0));
        assertEquals(savedTransactionDTOs.get(1), transactions.get(1));
    }
}