package com.example.opd.controllers;

import com.example.opd.models.User;
import com.example.opd.models.enums.Role;
import com.example.opd.services.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("allUsers", userService.getAllUsers());
        return "admin";
    }
@PostMapping("/admin/ban/{id}")
    public String banUser(@PathVariable("id")Long id){
        userService.banUser(id);
        return "redirect:/admin";
}
    @PostMapping("/admin/unban/{id}")
    public String unbanUser(@PathVariable("id")Long id){
        userService.unBanUser(id);
        return "redirect:/admin";
    }
    @GetMapping("/admin/edit/{user}")
    public String editUser(@PathVariable("user") User user, Model model){
        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());
        return "admin-edit";
    }
    @PostMapping("/admin/edit")
    public String userEdit(@RequestParam("userId") User user, @RequestParam Map<String, String> form){
        userService.changeRole(user,form);
        return "redirect:/admin";
    }
}
