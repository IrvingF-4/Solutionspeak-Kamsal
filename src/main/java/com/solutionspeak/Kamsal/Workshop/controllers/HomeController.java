package com.solutionspeak.Kamsal.Workshop.controllers;

import com.solutionspeak.Kamsal.Workshop.dto.PartDTO;
import com.solutionspeak.Kamsal.Workshop.dto.UserDTO;
import com.solutionspeak.Kamsal.Workshop.service.PartService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/")
@RequiredArgsConstructor
public class HomeController {

    private final PartService partService;

    @GetMapping
    public String showHomePage(Model model, HttpSession session) {
        List<PartDTO> parts = partService.getAllParts();
        if (parts.isEmpty()) {
            model.addAttribute("message", "Aún no se han agregado piezas.");
        } else {
            model.addAttribute("parts", parts);
        }
        UserDTO usuario;
        boolean role = false;
        boolean isLogged = false;
        if (session.getAttribute("usuario") != null) {
            usuario = (UserDTO) session.getAttribute("usuario");
            role = usuario.getRole().getKey() == 0;
            isLogged = true;
        }
        model.addAttribute("esForeman", role);
        model.addAttribute("isLogged", isLogged);
        return "index";
    }

    @GetMapping("logout")
    public String logout(HttpSession session, Model model) {
        session.invalidate();
        return "redirect:/";
    }

}
