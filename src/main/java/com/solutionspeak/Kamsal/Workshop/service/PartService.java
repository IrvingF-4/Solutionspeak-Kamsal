package com.solutionspeak.Kamsal.Workshop.service;

import com.solutionspeak.Kamsal.Workshop.dto.PartDTO;
import com.solutionspeak.Kamsal.Workshop.dto.WorkshopDTO;
import com.solutionspeak.Kamsal.Workshop.entity.Part;
import com.solutionspeak.Kamsal.Workshop.form.PartForm;
import com.solutionspeak.Kamsal.Workshop.repository.PartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PartService {

    private final PartRepository partRepository;
    private final WorkshopService workshopService;

    public List<PartDTO> getAllParts() {
        final List<Part> parts = partRepository.findAll();
        final Map<Integer, WorkshopDTO> workshopDTOMap =
                getWorkshopMap(parts.stream().map(Part::getWorkshopId).toList());
        return parts
                .stream()
                .map(part -> PartDTO
                        .build(part,
                                workshopDTOMap
                                        .get(part.getWorkshopId())))
                .toList();
    }

    public PartDTO getPartById(final int partId) throws Exception {
        validateIfPartExist(partId);
        final Part part = partRepository.findById(partId).orElseThrow();
        final WorkshopDTO workshopDTO = workshopService.getWorkshopById(part.getWorkshopId());
        return PartDTO.build(part, workshopDTO);
    }

    public PartDTO createPart(final PartForm form) throws Exception {
        workshopService.validateIfWorkshopExist(form.getWorkshopId());
        final Part part = new Part(form);
        partRepository.save(part);
        final WorkshopDTO workshopDTO = workshopService.getWorkshopById(part.getWorkshopId());
        return PartDTO.build(part, workshopDTO);
    }

    public PartDTO updatePart(final PartForm form, final int partId) throws Exception {
        validateIfPartExist(partId);
        workshopService.validateIfWorkshopExist(form.getWorkshopId());
        final Part part = partRepository.findById(partId).orElseThrow();
        final WorkshopDTO workshopDTO = workshopService.getWorkshopById(part.getWorkshopId());
        part.updatePart(form);
        partRepository.save(part);
        return PartDTO.build(part, workshopDTO);
    }

    public void deletePart(final int partId) throws Exception {
        validateIfPartExist(partId);
        partRepository.deleteById(partId);
    }

    public void validateIfPartExist(final int partId) throws Exception {
        if (!partRepository.existsById(partId)) {
            throw new Exception("No se ha encontrado la parte con el ID: " + partId);
        }
    }

    private Map<Integer, WorkshopDTO> getWorkshopMap(final List<Integer> workshopsId) {
        return workshopService.getWorkshopsByIds(workshopsId);
    }
}
