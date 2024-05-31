package com.solutionspeak.Kamsal.Workshop.controllers;

import com.solutionspeak.Kamsal.Workshop.dto.UserDTO;
import com.solutionspeak.Kamsal.Workshop.dto.WorkshopDTO;
import com.solutionspeak.Kamsal.Workshop.form.WorkshopForm;
import com.solutionspeak.Kamsal.Workshop.service.UserService;
import com.solutionspeak.Kamsal.Workshop.service.WorkshopService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/workshop/")
@RequiredArgsConstructor
public class WorkshopController {

    private final WorkshopService workshopService;
    private final UserService userService;

    @GetMapping("workshop")
    public String listWorkshops(Model model, HttpSession session) {
        final List<WorkshopDTO> workshos = workshopService.findAllWorkshops();
        final List<UserDTO> users = userService.findAllusers();
        model.addAttribute("workshops", workshos);
        boolean role = false;
        UserDTO usuario = null;
        boolean isLogged = false;
        if (session.getAttribute("usuario") != null) {
            usuario = (UserDTO) session.getAttribute("usuario");
            role = usuario.getRole().getKey() == 0 || usuario.getRole().getKey() == 2;
            model.addAttribute("usuario", usuario);
            int id = usuario.getId();
            isLogged = true;
            model.addAttribute("userId", id);
        }
        model.addAttribute("esForeman", role);
        model.addAttribute("isLogged", isLogged);
        model.addAttribute("users", users);
        return "workshop/workshop";
    }

    @PostMapping("register")
    public String createWorkshop(@Valid WorkshopForm form, Model model, HttpSession session) throws Exception {
        workshopService.createWorkshop(form);
        return "redirect:/workshop/workshop";
    }

    @PostMapping("update/{id}")
    public String editWorkshop(final @Valid WorkshopForm form, final @PathVariable("id") int id) throws Exception {
        workshopService.updateWorkshop(form, id);
        return "redirect:/workshop/workshop";
    }


    @PostMapping("delete/{id}")
    public String deleteWorkshop(final @PathVariable("id") int id) throws Exception {
        workshopService.deleteWorkshop(id);
        return "redirect:/workshop/workshop";
    }

}
