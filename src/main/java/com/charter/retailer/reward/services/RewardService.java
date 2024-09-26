package com.charter.retailer.reward.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.charter.retailer.reward.dto.TransactionDTO;
import static com.charter.retailer.reward.util.RewardConstants.INVALID_TRANSACTION_DATE_ERROR;

@Service
public class RewardService {

    @Autowired
    TransactionService transactionService;
    
    public Double getRewardsBetweenDate(java.time.LocalDate startDate, java.time.LocalDate endDate) {
        List<TransactionDTO> transactionsBetweenDates = transactionService.getTransactionBetweenDate(startDate, endDate);
        return calculateReward(transactionsBetweenDates);
    }

    public Double getTotalReward() {
        List<TransactionDTO> allTransactions = transactionService.getAllTransactions();
        return calculateReward(allTransactions);
    }


    public Double calculateReward(List<TransactionDTO> transactions) throws ResponseStatusException {
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, INVALID_TRANSACTION_DATE_ERROR);
        }
        return reward;
    }
}
