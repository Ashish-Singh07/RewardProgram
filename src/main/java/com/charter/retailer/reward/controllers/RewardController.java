package com.charter.retailer.reward.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.charter.retailer.reward.services.RewardService;
import static com.charter.retailer.reward.util.RewardConstants.INVALID_SEARCH_ENTITY_ERROR;


@RestController
@RequestMapping("/reward")
public class RewardController {
    @Autowired
    RewardService rewardService;
    
    @GetMapping("/betweenDate")
    public Double getRewardsBetweenDate(@RequestParam("startDate") java.time.LocalDate startDate, @RequestParam("endDate") java.time.LocalDate endDate) {
        return rewardService.getRewardsBetweenDate(startDate, endDate);
    }

    @GetMapping("/fromDate")
    public Double getRewardsFromDate(@RequestParam("startDate") java.time.LocalDate startDate) {
        java.time.LocalDate endDate = java.time.LocalDate.now();
        return rewardService.getRewardsBetweenDate(startDate, endDate);
    }

    @GetMapping("/forLast/{n}/{entity}")
    public Double getRewardsForDays(@PathVariable int n, @PathVariable String entity) {
        java.time.LocalDate startDate = switch (entity) {
                case "day" -> java.time.LocalDate.now().minusDays(n);
                case "week" -> java.time.LocalDate.now().minusWeeks(n);
                case "month" -> java.time.LocalDate.now().minusMonths(n);
                case "year" -> java.time.LocalDate.now().minusYears(n);
                default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, INVALID_SEARCH_ENTITY_ERROR);
            };
        java.time.LocalDate endDate = java.time.LocalDate.now();
        return rewardService.getRewardsBetweenDate(startDate, endDate);
    }

    @GetMapping("/totalRewards")
    public Double getTotalReward() {
        return rewardService.getTotalReward();
    }
}
