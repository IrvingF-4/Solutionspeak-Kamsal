package com.solutionspeak.Kamsal.Workshop.service;

import com.solutionspeak.Kamsal.Workshop.dto.WorkshopDTO;
import com.solutionspeak.Kamsal.Workshop.entity.Workshop;
import com.solutionspeak.Kamsal.Workshop.repository.WorkshopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkshopService {

    private final WorkshopRepository workshopRepository;

    public List<WorkshopDTO> findAllWorkshops() {
        final List<Workshop> workshops = workshopRepository.findAll();
        return workshops.stream().map(WorkshopDTO::build).toList();
    }

    public WorkshopDTO getWorkshopById(final int workshopId) throws Exception {
        validateIfWorkshopExist(workshopId);
        final Workshop workshop = workshopRepository.findById(workshopId).orElseThrow();
        return WorkshopDTO.build(workshop);
    }

    public void validateIfWorkshopExist(final int workshopId) throws Exception {
        if (!workshopRepository.existsById(workshopId)) {
            throw new Exception("No se ha encontrado el Taller con el siguiente ID: " + workshopId);
        }
    }
}
