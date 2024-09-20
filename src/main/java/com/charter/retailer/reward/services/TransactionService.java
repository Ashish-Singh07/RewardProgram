package com.charter.retailer.reward.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charter.retailer.reward.dto.TransactionDTO;
import com.charter.retailer.reward.entities.TransactionEntity;
import com.charter.retailer.reward.repositories.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    ModelMapper modelMapper;
    
    public List<TransactionDTO> getAllTransactions() {
        List<TransactionEntity> transactionEntities = transactionRepository.findAll();
        return transactionEntities.stream().map(entity -> modelMapper.map(entity, TransactionDTO.class)).toList();
    }

    public TransactionDTO getTransactionById(Long id) {
        TransactionEntity transactionEntity = transactionRepository.findById(id).get();
        return modelMapper.map(transactionEntity, TransactionDTO.class);
    }

    public List<TransactionDTO> getTransactionByMonth(int month) {
        List<TransactionEntity> transactionEntities = transactionRepository.findByTransactionMonth(month);
        return transactionEntities.stream().map(entity -> modelMapper.map(entity, TransactionDTO.class)).toList();
    }

    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        TransactionEntity transactionEntity = modelMapper.map(transactionDTO, TransactionEntity.class);
        transactionRepository.save(transactionEntity);
        return modelMapper.map(transactionEntity, TransactionDTO.class);
    }
    
    public List<TransactionDTO> createAllTransactions(List<TransactionDTO> transactionDTOs) {
        List<TransactionEntity> transactionEntities = transactionDTOs.stream().map(transactionDTO -> modelMapper.map(transactionDTO, TransactionEntity.class)).toList();
        transactionRepository.saveAll(transactionEntities);
        return transactionEntities.stream().map(entity -> modelMapper.map(entity, TransactionDTO.class)).toList();
    }
}
