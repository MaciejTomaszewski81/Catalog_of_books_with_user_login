package com.example.demo.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.user.User;
import com.example.demo.user.UserService;

import java.util.List;

@Controller
public class AdministratorController {

    private final UserService userService;

    public AdministratorController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String administrationPanel(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "/admin";
    }
}