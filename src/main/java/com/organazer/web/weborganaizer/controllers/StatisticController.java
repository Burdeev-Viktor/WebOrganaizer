package com.organazer.web.weborganaizer.controllers;

import com.organazer.web.weborganaizer.model.User;
import com.organazer.web.weborganaizer.service.StatisticService;
import com.organazer.web.weborganaizer.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/statistic")
public class StatisticController {
    private final StatisticService statisticService;
    private final UserService userService;

    public StatisticController(StatisticService statisticService, UserService userService) {
        this.statisticService = statisticService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getStatistic(@AuthenticationPrincipal UserDetails userDetails,Model model){
        User user = userService.findUserByLogin(userDetails.getUsername());
        model.addAttribute("labStatistic",statisticService.getLabStatistic(user.getId()));
        model.addAttribute("gradeStatistic",statisticService.getGradeStatistic(user.getId()));
        return "statistic";
    }
}
