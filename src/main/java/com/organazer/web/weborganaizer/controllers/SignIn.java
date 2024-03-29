package com.organazer.web.weborganaizer.controllers;

import com.organazer.web.weborganaizer.model.User;
import com.organazer.web.weborganaizer.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignIn {
    private final UserService userService;

    public SignIn(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
    @PostMapping("/registration")
    public String registration(User user){
        if(userService.userIsExistsByLoginAndPassword(user)){
            return "registration";
        }
        user.setName(user.getLogin());
        userService.save(user);
        return "redirect:/set-week-count";
    }

}
