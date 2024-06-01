package com.contactmanager.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PageController {

    @GetMapping("/about")
    public String getAboutPage() {
        System.out.println("This is a Page controller we are testing About page request");
        return "about";
    }

    @GetMapping("/services")
    public String services() {
        System.out.println("This is a Page controller we are testing Services page request");
        return "services";
    }

    @GetMapping("/contact")
    public String contact() {
        System.out.println("This is a Page controller we are testing Contact page request");
        return "contact";
    }

    @GetMapping("/home")
    public String home() {
        System.out.println("This is a Page controller we are testing Home page request");
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        System.out.println("This is a Page controller we are testing Home page request");
        return "login";
    }

}
