package com.charter.retailer.reward.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charter.retailer.reward.dto.TransactionDTO;
import com.charter.retailer.reward.entities.TransactionEntity;
import com.charter.retailer.reward.exception.CreateTransactionException;
import com.charter.retailer.reward.exception.InvalidDatesException;
import com.charter.retailer.reward.exception.TransactionNotFoundException;
import com.charter.retailer.reward.repositories.TransactionRepository;
import static com.charter.retailer.reward.util.RewardConstants.INVALID_TRANSACTION_DATE_ERROR;
import static com.charter.retailer.reward.util.RewardConstants.NO_TRANSACTION_ERROR;
import static com.charter.retailer.reward.util.RewardConstants.TRANSACTION_CREATION_FAILED;
import static com.charter.retailer.reward.util.RewardConstants.TRANSACTION_NOT_FOUND_ERROR;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    ModelMapper modelMapper;
    
    public List<TransactionDTO> getAllTransactions() {
        List<TransactionEntity> transactionEntities = transactionRepository.findAll();
        if(transactionEntities.isEmpty()) {
            throw new TransactionNotFoundException(NO_TRANSACTION_ERROR);
        }
        return transactionEntities.stream().map(entity -> modelMapper.map(entity, TransactionDTO.class)).toList();
    }

    public TransactionDTO getTransactionById(Long id) {
        TransactionEntity transactionEntity;
        try {
            transactionEntity = transactionRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new TransactionNotFoundException(TRANSACTION_NOT_FOUND_ERROR);
        }
        return modelMapper.map(transactionEntity, TransactionDTO.class);
    }

    public List<TransactionDTO> getTransactionBetweenDate(java.time.LocalDate startDate, java.time.LocalDate endDate) {
        List<TransactionEntity> transactionEntities;
        try {
            transactionEntities = transactionRepository.findByTransactionDateBetween(startDate, endDate);
        } catch (Exception e) {
            throw new InvalidDatesException(INVALID_TRANSACTION_DATE_ERROR);
        }
        if(transactionEntities.isEmpty()) {
            throw new InvalidDatesException(INVALID_TRANSACTION_DATE_ERROR);
        }
        return transactionEntities.stream().map(entity -> modelMapper.map(entity, TransactionDTO.class)).toList();
    }

    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        TransactionEntity transactionEntity = modelMapper.map(transactionDTO, TransactionEntity.class);
        try {
            transactionRepository.save(transactionEntity);
        } catch (Exception e) {
            throw new CreateTransactionException(TRANSACTION_CREATION_FAILED);
        }
        return modelMapper.map(transactionEntity, TransactionDTO.class);
    }
    
    public List<TransactionDTO> createAllTransactions(List<TransactionDTO> transactionDTOs) {
        List<TransactionEntity> transactionEntities = transactionDTOs.stream().map(transactionDTO -> modelMapper.map(transactionDTO, TransactionEntity.class)).toList();
        try {
            transactionRepository.saveAll(transactionEntities);
        } catch (Exception e) {
            throw new CreateTransactionException(TRANSACTION_CREATION_FAILED);
        }
        return transactionEntities.stream().map(entity -> modelMapper.map(entity, TransactionDTO.class)).toList();
    }
}
