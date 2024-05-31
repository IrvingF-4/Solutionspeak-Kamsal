package com.solutionspeak.Kamsal.Workshop.service;

import com.solutionspeak.Kamsal.Workshop.dto.UserDTO;
import com.solutionspeak.Kamsal.Workshop.dto.WorkshopDTO;
import com.solutionspeak.Kamsal.Workshop.entity.Workshop;
import com.solutionspeak.Kamsal.Workshop.form.WorkshopForm;
import com.solutionspeak.Kamsal.Workshop.repository.WorkshopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkshopService {

    private final WorkshopRepository workshopRepository;
    private final UserService userService;

    public List<WorkshopDTO> findAllWorkshops() {
        final List<Workshop> workshops = workshopRepository.findAll();
        final Map<Integer, UserDTO> userDTOMap =
                getUsersMap(workshops.stream().map(Workshop::getUserId).toList());
        return workshops
                .stream()
                .map(workshop -> WorkshopDTO
                        .build(workshop,
                                userDTOMap.get(workshop.getUserId())
                        )).toList();
    }

    public WorkshopDTO getWorkshopById(final int workshopId) throws Exception {
        validateIfWorkshopExist(workshopId);
        final Workshop workshop = workshopRepository.findById(workshopId).orElseThrow();
        final UserDTO userDTO = userService.getUserById(workshop.getUserId());
        return WorkshopDTO.build(workshop, userDTO);
    }

    public WorkshopDTO createWorkshop(final WorkshopForm form) throws Exception {
        userService.validateIfUserExist(form.getUserId());
        final Workshop workshop = new Workshop(form);
        workshopRepository.save(workshop);
        final UserDTO userDTO = userService.getUserById(workshop.getUserId());
        return WorkshopDTO.build(workshop, userDTO);
    }

    public WorkshopDTO updateWorkshop(final WorkshopForm form, final int workshopId)
            throws Exception {
        validateIfWorkshopExist(workshopId);
        userService.validateIfUserExist(form.getUserId());
        final Workshop workshop = workshopRepository.findById(workshopId).orElseThrow();
        final UserDTO userDTO = userService.getUserById(workshop.getUserId());
        workshop.updateWorkshop(form);
        workshopRepository.save(workshop);
        return WorkshopDTO.build(workshop, userDTO);
    }

    public void deleteWorkshop(final int workshopId) throws Exception {
        validateIfWorkshopExist(workshopId);
        workshopRepository.deleteById(workshopId);
    }

    public void validateIfWorkshopExist(final int workshopId) throws Exception {
        if (!workshopRepository.existsById(workshopId)) {
            throw new Exception("No se ha encontrado el Taller con el siguiente ID: " + workshopId);
        }
    }

    private Map<Integer, UserDTO> getUsersMap(final List<Integer> usersId) {
        return userService.getUserByIds(usersId);
    }

    public Map<Integer, WorkshopDTO> getWorkshopsByIds(final List<Integer> workshopsId) {
        final List<Workshop> workshops = workshopRepository.findAllById(workshopsId);
        return workshopDTOs(workshops);
    }

    private Map<Integer, WorkshopDTO> workshopDTOs(final List<Workshop> workshops) {
        final List<WorkshopDTO> workshopDTOS =
                workshops.stream().map(WorkshopDTO::build).toList();
        return workshopDTOS
                .stream()
                .collect(Collectors.toMap(WorkshopDTO::getId, Function.identity()));
    }
}
