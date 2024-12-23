package ma.youcode.citronix.services.implementations;

import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import ma.youcode.citronix.dto.request.harvest.HarvestCreateDTO;
import ma.youcode.citronix.dto.request.harvest.HarvestUpdateDTO;
import ma.youcode.citronix.dto.response.harvest.HarvestResponseDTO;
import ma.youcode.citronix.entities.Harvest;
import ma.youcode.citronix.enums.ErrorType;
import ma.youcode.citronix.exceptions.harvest.HarvestNotFoundException;
import ma.youcode.citronix.mappers.HarvestMapper;
import ma.youcode.citronix.repositories.HarvestRepository;
import ma.youcode.citronix.services.interfaces.HarvestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class HarvestServiceImpl implements HarvestService {

    private final HarvestRepository repository;
    private final HarvestMapper mapper;


    @Override
    public HarvestResponseDTO create(HarvestCreateDTO createDTO) {

        if (isHarvested(createDTO)) {
            throw new EntityExistsException(ErrorType.DUPLICATE_HARVEST_FIELD.getMessage(createDTO.seasonType() , createDTO.year()));
        }

        Harvest toHarvest = mapper.fromCreateDTO(createDTO);
        Harvest savedHarvest = repository.save(toHarvest);

        return mapper.toResponseDTO(repository.save(savedHarvest));

    }

    @Override
    public HarvestResponseDTO update(HarvestUpdateDTO updateDTO , Long harvestId) {

        Harvest harvest = repository.findById(harvestId)
                .orElseThrow(() -> new HarvestNotFoundException(ErrorType.NOT_FOUND.getMessage("Harvest")));

        Harvest toHarvest = mapper.fromUpdateDTO(updateDTO);
        toHarvest.setId(harvestId);
        toHarvest.setUpdatedAt(LocalDateTime.now());
        toHarvest.setHarvestHistories(harvest.getHarvestHistories());

        return mapper.toResponseDTO(repository.save(toHarvest));

    }

    @Override
    public HarvestResponseDTO delete(Long harvestId) {

        Harvest harvest = repository.findById(harvestId)
                .orElseThrow(() -> new HarvestNotFoundException(ErrorType.NOT_FOUND.getMessage("Harvest")));
        repository.delete(harvest);
        return mapper.toResponseDTO(harvest);

    }

    @Override
    public HarvestResponseDTO read(Long harvestId) {
        Harvest harvest = repository.findById(harvestId)
                .orElseThrow(() -> new HarvestNotFoundException(ErrorType.NOT_FOUND.getMessage("Harvest")));
        return mapper.toResponseDTO(harvest);
    }

    @Override
    public Page<HarvestResponseDTO> readAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Harvest> harvests = repository.findAll(pageable);

        return harvests.map(mapper::toResponseDTO);
    }

    public Harvest getHarvestById(Long harvestId) {
        return repository.findById(harvestId)
                .orElseThrow(() -> new HarvestNotFoundException(ErrorType.NOT_FOUND.getMessage("Harvest")));
    }

    private boolean isHarvested(HarvestCreateDTO createDTO) {
        return repository.isFieldHarvested(createDTO.seasonType() , createDTO.year() , createDTO.fieldId());
    }

}
