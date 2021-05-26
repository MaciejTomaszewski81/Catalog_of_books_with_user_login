package com.example.demo.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.user.RoleDto;
import com.example.demo.user.User;
import com.example.demo.user.UserDto;
import com.example.demo.user.UserService;

import java.security.Principal;

@Controller
public class SecurityController {

    private final UserService userService;
    private final RoleDto userDtoService;

    public SecurityController(UserService userService, RoleDto userDtoService) {
        this.userService = userService;
        this.userDtoService = userDtoService;
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String error, Model model) {
        boolean errorInformation = false;
        if (error != null) {
            errorInformation = true;
        }
        model.addAttribute("showErrorMessage", errorInformation);
        return "/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "/register";
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        userService.registerUser(email, password);
        return "/login";
    }

    @GetMapping("/edit-password")
    public String editUserPassword(Model model, Principal principal) {
        User userToEdit = userService.findByEmail(principal.getName());
        model.addAttribute("user", userToEdit);
        return "/editPassword";
    }

    @PostMapping("/edit-password")
    public String saveNewPassword(User user, @RequestParam String password) {
        userService.updatePassword(user, password);
        return "redirect:/home";
    }

    @GetMapping("/edit-user")
    public String editUser(Model model, Principal principal) {
        User userToEdit = userService.findByEmail(principal.getName());
        model.addAttribute("user", userToEdit);
        return "/edituser";
    }

    @PostMapping("/edit-user")
    public String editAndSaveUser(@RequestParam Long id, @RequestParam String email) {
        userService.updateUser(id, email);
        return "redirect:/logout";
    }

    @GetMapping("/{id}/edit-user")
    public String getUser(@PathVariable Long id, Model model) {
        UserDto userDto = userService.getUserDtoFromUser(id);
        model.addAttribute("user", userDto);
        return "editUserRole";
    }

    @PostMapping("/{id}/edit-user")
    public String editUser(UserDto userDto) {
        userDtoService.saveRoleForUser(userDto);
        return "redirect:/admin";
    }
}
