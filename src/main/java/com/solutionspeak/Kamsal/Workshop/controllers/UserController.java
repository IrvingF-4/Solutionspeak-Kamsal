package com.solutionspeak.Kamsal.Workshop.controllers;

import com.solutionspeak.Kamsal.Workshop.dto.UserDTO;
import com.solutionspeak.Kamsal.Workshop.form.UserForm;
import com.solutionspeak.Kamsal.Workshop.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("register")
    public String showRegistrationForm(Model model) {

        return "user/register";
    }

    @PostMapping
    public String registerUser(@Valid UserForm form, Model model) {
        userService.createUser(form);
        return "user/login";
    }

    @GetMapping({"/login", "/login.html"})
    public String showPageLoginUser(Model model) {
        return "user/login";
    }

    @PostMapping("/login")
    public String loginUser(Model model, @RequestParam String email, @RequestParam String password,
                            HttpSession session) throws Exception {
        UserDTO user = userService.loginUser(email, password);
        session.setAttribute("usuario", user);
        return "redirect:../";
    }
}