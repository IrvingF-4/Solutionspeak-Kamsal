package com.solutionspeak.Kamsal.Workshop.controllers;

import com.solutionspeak.Kamsal.Workshop.dto.PartDTO;
import com.solutionspeak.Kamsal.Workshop.dto.UserDTO;
import com.solutionspeak.Kamsal.Workshop.dto.WorkshopDTO;
import com.solutionspeak.Kamsal.Workshop.form.PartForm;
import com.solutionspeak.Kamsal.Workshop.service.PartService;
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
@RequestMapping(path = "/part/")
@RequiredArgsConstructor
public class PartController {

    private final PartService partService;
    private final WorkshopService workshopService;

    @GetMapping("parts")
    public String showParts(Model model, HttpSession session) {
        final List<PartDTO> parts = partService.getAllParts();
        final List<WorkshopDTO> workshops = workshopService.findAllWorkshops();
        model.addAttribute("parts", parts);
        model.addAttribute("workshops", workshops);
        UserDTO usuario = null;
        boolean role = false;
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
        return "part/parts";
    }

    @PostMapping("register")
    public String createPart(final @Valid PartForm form, Model model) throws Exception {
        partService.createPart(form);
        return "redirect:/part/parts";
    }

    @PostMapping("update/{id}")
    public String updatePart(final @Valid PartForm form, final @PathVariable("id") int id) throws Exception {
        partService.updatePart(form, id);
        return "redirect:/part/parts";
    }

    @PostMapping("delete/{id}")
    public String deletePart(@PathVariable("id") int id) throws Exception {
        partService.deletePart(id);
        return "redirect:/part/parts";
    }

}
