package com.solutionspeak.Kamsal.Workshop.controllers;

import com.solutionspeak.Kamsal.Workshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;


}