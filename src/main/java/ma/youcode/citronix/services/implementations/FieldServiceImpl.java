package ma.youcode.citronix.services.implementations;

import lombok.AllArgsConstructor;
import ma.youcode.citronix.dto.request.field.FieldCreateDTO;
import ma.youcode.citronix.dto.request.field.FieldUpdateDTO;
import ma.youcode.citronix.dto.response.field.FieldResponseDTO;
import ma.youcode.citronix.entities.Farm;
import ma.youcode.citronix.entities.Field;
import ma.youcode.citronix.enums.ErrorType;
import ma.youcode.citronix.exceptions.field.FieldNotFoundException;
import ma.youcode.citronix.exceptions.field.FieldSurfaceToLargeException;
import ma.youcode.citronix.exceptions.field.MaxFieldsException;
import ma.youcode.citronix.mappers.FieldMapper;
import ma.youcode.citronix.repositories.FieldRepository;
import ma.youcode.citronix.services.interfaces.FarmService;
import ma.youcode.citronix.services.interfaces.FieldService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FieldServiceImpl implements FieldService {

    private static final Logger log = LogManager.getLogger(FieldServiceImpl.class);
    private final FieldRepository repository;
    private final FieldMapper mapper;
    private final FarmService farmService;


    @Override
    public FieldResponseDTO create(FieldCreateDTO createDTO) {
        Farm farm = farmService.getFarmById(createDTO.farmId());

        verifyFarmSpace(farm , createDTO.surface());
        validateFieldMaxSurface(farm.getFields().size());
        validateFieldSurface(createDTO.surface() , farm.getSurface());

        Field field = mapper.fromCreateDTO(createDTO);
        field.setFarm(farm);
        Field savedField = repository.save(field);

        return mapper.toResponseDTO(savedField);

    }

    @Override
    public FieldResponseDTO update(FieldUpdateDTO dto , Long fieldId) {

        Field field = repository.findById(fieldId)
                .orElseThrow(() -> new FieldNotFoundException(ErrorType.NOT_FOUND.getMessage("Field")));

        if (!dto.hasData()) {
            throw new FieldNotFoundException(ErrorType.NO_DATA_PROVIDED.getMessage());
        }

        if (dto.surface() != null) {
            validateFieldSurface(dto.surface() , field.getFarm().getSurface());
            verifyFieldSpaceForTree(field , dto.surface());
            field.setSurface(dto.surface());
        }
        if (dto.farmId() != null) {
            Farm farm = farmService.getFarmById(dto.farmId());
            field.setFarm(farm);
        }

        field.setUpdatedAt(LocalDateTime.now());
        Field updatedField = repository.save(field);

        return mapper.toResponseDTO(updatedField);
    }

    @Override
    public FieldResponseDTO delete(Long fieldId) {

        Field field = repository.findById(fieldId)
                .orElseThrow(() -> new FieldNotFoundException(ErrorType.NOT_FOUND.getMessage("Field")));
        repository.delete(field);
        return mapper.toResponseDTO(field);

    }

    @Override
    public FieldResponseDTO read(Long fieldId) {
        Field field = repository.findById(fieldId)
                .orElseThrow(() -> new FieldNotFoundException(ErrorType.NOT_FOUND.getMessage("Field")));
        return mapper.toResponseDTO(field);
    }

    @Override
    public Page<FieldResponseDTO> readAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Field> fields = repository.findAll(pageable);

        return fields.map(mapper::toResponseDTO);
    }

    public Field getFieldById(Long fieldId) {
        return repository.findById(fieldId)
                .orElseThrow(() -> new FieldNotFoundException(ErrorType.NOT_FOUND.getMessage("Field")));
    }

    private void verifyFarmSpace(Farm farm , Integer fieldSurface) {
        Integer fieldsTotalSurface = repository.computeFieldsSurface(farm.getId());
        if (fieldsTotalSurface != null) {
            int availableSpace = farm.getSurface() - fieldsTotalSurface;
            if (availableSpace < fieldSurface) {
                throw new IllegalArgumentException(String.format("This any space for this new field on farm %s" , farm.getName()));
            }
        }

    }
    // field represent 50% from farm like a max value.
    private void validateFieldSurface(double fieldSurface , double farmSurface) {
        int maxFieldSurface = (int) (farmSurface / 2);

        if (fieldSurface > maxFieldSurface) {
            throw new FieldSurfaceToLargeException(ErrorType.FIELD_SIZE_LIMIT_EXCEEDED.getMessage(maxFieldSurface));
        }

    }

    /**
    this function Validate max surface for fields @Param int
    */
    private void validateFieldMaxSurface(int size ) {

        if (size == 10) {
            throw new MaxFieldsException(ErrorType.MAX_FIELD.getMessage());
        }

    }

    @Override
    public boolean canAddTree(Field field) {

        int maxAllowedTrees =  (int)((double) field.getSurface() / 10000 * 100);
        return field.getTrees().size() < maxAllowedTrees;

    }

    private void verifyFieldSpaceForTree( Field field, int newSurface) {
        int newSpaceAllowed = (int)((double) newSurface / 10000 * 100);
        log.warn(newSpaceAllowed);
        if (field.getTrees().size() > newSpaceAllowed) {
            throw new IllegalArgumentException(String.format(
                    "The field size of %d m² allows for a maximum of %d trees, but this field currently has %d trees.",
                    newSurface, newSpaceAllowed, field.getTrees().size()));
        }
    }


}
