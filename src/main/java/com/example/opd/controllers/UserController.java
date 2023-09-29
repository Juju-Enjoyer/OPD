package com.example.opd.controllers;

import com.example.opd.models.User;
import com.example.opd.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/registration")
    public String registration(){
         return "registration";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/registration")
    public String creatUser(User user, Model model){
        if(!userService.createUser(user)){
            model.addAttribute("errorMessage", "Пользователь с таким email: "+user.getEmail()+" уже существует");
                    return "registration";
        }
        return "redirect:/login";
    }
    @GetMapping("/hello")
    public String securityUrl(){
        return "hello";
    }
}
