package com.contactmanager.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/user")

public class UserController {

    @GetMapping("/dashboard")
    public String userDashborad()
    {
        return "user/dashboard";
    }

    @GetMapping("/profile")
    public String userProfile()
    {
        return "user/profile";
    }
}
