package com.charter.retailer.reward.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.charter.retailer.reward.exception.InvalidDatesException;
import com.charter.retailer.reward.services.RewardService;
import static com.charter.retailer.reward.util.RewardConstants.INVALID_SEARCH_ENTITY_ERROR;


@RestController
@RequestMapping("/reward")
public class RewardController {
    @Autowired
    RewardService rewardService;
    
    @GetMapping("/betweenDate")
    public ResponseEntity<Double> getRewardsBetweenDate(@RequestParam("startDate") java.time.LocalDate startDate, @RequestParam("endDate") java.time.LocalDate endDate) {
        return new ResponseEntity<>(rewardService.getRewardsBetweenDate(startDate, endDate), HttpStatus.OK);
    }

    @GetMapping("/fromDate")
    public ResponseEntity<Double> getRewardsFromDate(@RequestParam("startDate") java.time.LocalDate startDate) {
        java.time.LocalDate endDate = java.time.LocalDate.now();
        return new ResponseEntity<>(rewardService.getRewardsBetweenDate(startDate, endDate), HttpStatus.OK);
    }

    @GetMapping("/forLast/{n}/{time}")
    public ResponseEntity<Double> getRewardsForDays(@PathVariable int n, @PathVariable String time) {
        java.time.LocalDate startDate = switch (time) {
                case "day" -> java.time.LocalDate.now().minusDays(n);
                case "week" -> java.time.LocalDate.now().minusWeeks(n);
                case "month" -> java.time.LocalDate.now().minusMonths(n);
                case "year" -> java.time.LocalDate.now().minusYears(n);
                default -> throw new InvalidDatesException(INVALID_SEARCH_ENTITY_ERROR);
            };
        java.time.LocalDate endDate = java.time.LocalDate.now();
        return new ResponseEntity<>(rewardService.getRewardsBetweenDate(startDate, endDate), HttpStatus.OK);
    }

    @GetMapping("/totalRewards")
    public ResponseEntity<Double> getTotalRewards() {
        return new ResponseEntity<>(rewardService.getTotalReward(), HttpStatus.OK);
    }
}
