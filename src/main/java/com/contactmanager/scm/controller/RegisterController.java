package com.contactmanager.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.contactmanager.scm.entities.User;
import com.contactmanager.scm.helpers.Message;
import com.contactmanager.scm.helpers.MessageType;
import com.contactmanager.scm.services.UserServices;
import com.contactmanager.scm.util.UserForm;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    private UserServices uService;

    @GetMapping("/register")
    public String register(Model model) {
        //We can also insert default data as well.
        UserForm user = new UserForm();
        model.addAttribute("user", user);
        return "Register";
    }

    @PostMapping("/do-register")
    public String doRegister(@Valid @ModelAttribute("user") UserForm userData, BindingResult bindRes, Model model, HttpSession session) {
        System.out.println("This is a Register controller we are testing Home page request");
        System.out.println(userData);
        System.out.println(bindRes.getAllErrors());

        //Server side validation
        if(bindRes.hasErrors())
        {
            return "Register";
        }
    
    

        //userData to User Object Creation. using builder
        // User usr = User.builder()
        // .firstName(userData.getFirstName())
        // .lastName(userData.getLastName())
        // .email(userData.getEmail())
        // .password(userData.getPassword())
        // .phone(userData.getPhone())
        // .about(userData.getAbout())
        // .build();

        User usr = new User();
        usr.setFirstName(userData.getFirstName());
        usr.setLastName(userData.getLastName());
        usr.setEmail(userData.getEmail());
        usr.setPassword(userData.getPassword());
        usr.setPhone(userData.getPhone());
        usr.setAbout(userData.getAbout());

        User savedUser = uService.saveUser(usr);

        Message msg = Message.builder().content("Registration Successful for User : "+usr.getFirstName()).type(MessageType.green).build();

        session.setAttribute("message", msg);

        System.out.println("user saved : "+savedUser);
        return "redirect:/register";
    }

}
