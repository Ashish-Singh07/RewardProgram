package com.charter.retailer.reward.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.charter.retailer.reward.dto.TransactionDTO;

@Service
public class RewardService {

    @Autowired
    TransactionService transactionService;

    public Double getRewardByMonth(int month) {
        List<TransactionDTO> transactionByMonth = transactionService.getTransactionByMonth(month);
        return calculateReward(transactionByMonth);
    }

    public Double calculateReward(List<TransactionDTO> transactionByMonth) throws ResponseStatusException {
        Double reward = 0.0;
        if (!transactionByMonth.isEmpty()) {
            Double totalTransactionSum = transactionByMonth.stream().mapToDouble(TransactionDTO::getTransactionAmount).sum();
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The month selected is not valid, please select a valid month");
        }   

        return reward;
    }

    public Double calculateRewardByMonth(List<TransactionDTO> transactionByMonth) throws ResponseStatusException {
        Double reward = 0.0;
        if (!transactionByMonth.isEmpty()) {
            Double totalTransactionSum = transactionByMonth.stream().mapToDouble(TransactionDTO::getTransactionAmount).sum();
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The month selected is not valid, please select a valid month");
        }   

        return reward;
    }
}
