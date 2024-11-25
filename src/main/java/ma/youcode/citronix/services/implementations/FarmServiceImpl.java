package ma.youcode.citronix.services.implementations;

import lombok.extern.log4j.Log4j2;
import ma.youcode.citronix.dto.request.farm.FilterFarmDTO;
import ma.youcode.citronix.entities.Field;
import ma.youcode.citronix.enums.ErrorType;
import ma.youcode.citronix.exceptions.farm.FarmNotFoundException;
import lombok.AllArgsConstructor;
import ma.youcode.citronix.dto.request.farm.FarmCreateDTO;
import ma.youcode.citronix.dto.request.farm.FarmUpdateDTO;
import ma.youcode.citronix.dto.response.farm.FarmResponseDTO;
import ma.youcode.citronix.entities.Farm;
import ma.youcode.citronix.mappers.FarmMapper;
import ma.youcode.citronix.repositories.FarmRepository;
import ma.youcode.citronix.services.interfaces.FarmService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

import static ma.youcode.citronix.repositories.specification.FarmSpecification.filterFarm;

@Log4j2
@Service
@AllArgsConstructor
public class FarmServiceImpl implements FarmService {

    private final FarmRepository repository;
    private final FarmMapper mapper;


    @Override
    public FarmResponseDTO create(FarmCreateDTO createDTO) {

        Farm farm = mapper.fromCreateDTO(createDTO);
        return mapper.toResponseDTO(repository.save(farm));

    }

    @Override
    public FarmResponseDTO update(FarmUpdateDTO dto, Long farmId) {

        Farm farm = repository.findById(farmId)
                .orElseThrow(() -> new FarmNotFoundException(ErrorType.NOT_FOUND.getMessage("Farm")));

        if (!dto.hasData()) {
            throw new FarmNotFoundException(ErrorType.NO_DATA_PROVIDED.getMessage());
        }

        if (dto.name() != null) {
            farm.setName(dto.name());
        }
        if (dto.surface() != null) {
            verifyValidSurface(farm, dto.surface());
            farm.setSurface(dto.surface());
        }
        if (dto.location() != null) {
            farm.setLocation(dto.location());
        }

        farm.setUpdatedAt(LocalDateTime.now());

        return mapper.toResponseDTO(repository.save(farm));
    }

    @Override
    public FarmResponseDTO delete(Long farmId) {

        Farm farm = repository.findById(farmId).orElseThrow(() -> new FarmNotFoundException(ErrorType.NOT_FOUND.getMessage("Farm")));
        repository.delete(farm);
        return mapper.toResponseDTO(farm);


    }

    @Override
    public FarmResponseDTO read(Long farmId) {
        Farm farm = repository.findById(farmId).orElseThrow(() -> new FarmNotFoundException(ErrorType.NOT_FOUND.getMessage("Farm")));
        return mapper.toResponseDTO(farm);
    }

    @Override
    public Page<FarmResponseDTO> readAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Farm> farms = repository.findAll(pageable);

        return farms.map(mapper::toResponseDTO);
    }

    public Farm getFarmById(Long farmId) {
        return repository.findById(farmId).orElseThrow(() -> new FarmNotFoundException(ErrorType.NOT_FOUND.getMessage("Farm")));
    }


    @Override
    public Page<FarmResponseDTO> filter(FilterFarmDTO filterDTO, int page, int size) {

        Specification<Farm> specification = filterFarm(filterDTO);
        Page<Farm> filteredFarms = repository.findAll(specification, PageRequest.of(page, size));
        return filteredFarms.map(mapper::toResponseDTO);

    }

    public void verifyValidSurface(Farm farm, int newSurface) {

        int usedSpaceOnFarm = calcUsedSpaceOnFarm(farm);

        if (usedSpaceOnFarm > newSurface) {
            throw new IllegalArgumentException(String.format(
                    "The new surface of %d is invalid because it must be at least %d.",
                    newSurface, usedSpaceOnFarm));
        }


    }

    private int calcUsedSpaceOnFarm(Farm farm) {
        return farm.getFields().stream()
                .mapToInt(Field::getSurface)
                .sum();

    }
}



