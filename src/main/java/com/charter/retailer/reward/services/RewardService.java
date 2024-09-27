package com.charter.retailer.reward.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charter.retailer.reward.dto.TransactionDTO;
import com.charter.retailer.reward.exception.InvalidDatesException;
import com.charter.retailer.reward.exception.TransactionNotFoundException;
import static com.charter.retailer.reward.util.RewardConstants.INVALID_TRANSACTION_DATE_ERROR;
import static com.charter.retailer.reward.util.RewardConstants.NO_TRANSACTION_ERROR;

@Service
public class RewardService {

    @Autowired
    TransactionService transactionService;
    
    public Double getRewardsBetweenDate(java.time.LocalDate startDate, java.time.LocalDate endDate) throws InvalidDatesException {
        List<TransactionDTO> transactionsBetweenDates;
        try {
            transactionsBetweenDates = transactionService.getTransactionBetweenDate(startDate, endDate);
        } catch (Exception e) {
            throw new InvalidDatesException(INVALID_TRANSACTION_DATE_ERROR);
        }
        return calculateReward(transactionsBetweenDates);
    }

    public Double getTotalReward() throws TransactionNotFoundException {
        List<TransactionDTO> allTransactions;
        try {
            allTransactions = transactionService.getAllTransactions();
        }
        catch (Exception e) {
            throw new TransactionNotFoundException(NO_TRANSACTION_ERROR);
        }
        
        return calculateReward(allTransactions);
    }


    public Double calculateReward(List<TransactionDTO> transactions) throws InvalidDatesException {
        Double reward = 0.0;
        if (!transactions.isEmpty()) {
            Double totalTransactionSum = transactions.stream().mapToDouble(TransactionDTO::getTransactionAmount).sum();
            if(totalTransactionSum < 50){
                reward = 0.0;
            }
            else if(totalTransactionSum >= 50 && totalTransactionSum < 100){
                reward = (totalTransactionSum - 50);
            }
            else if(totalTransactionSum >= 100){
                reward = (totalTransactionSum - 50) + (2*(totalTransactionSum - 100));
            }
        }
        else{
            throw new InvalidDatesException(INVALID_TRANSACTION_DATE_ERROR);
        }
        return reward;
    }
}
