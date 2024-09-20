package com.charter.retailer.reward.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.charter.retailer.reward.services.RewardService;


@RestController
@RequestMapping("/reward")
public class RewardController {
    @Autowired
    RewardService rewardService;
   
    @GetMapping("/month/{month}")
    public Double getRewardsByMonth(@PathVariable int month) {
        return rewardService.getRewardByMonth(month);
    }
}
