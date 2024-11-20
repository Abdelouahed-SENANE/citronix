package ma.youcode.citronix.services.implementations;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import ma.senane.utilities.generics.implementations.GenericServiceImpl;
import ma.youcode.citronix.dto.request.farm.FarmCreateDTO;
import ma.youcode.citronix.dto.request.farm.FarmUpdateDTO;
import ma.youcode.citronix.dto.response.farm.FarmResponseDTO;
import ma.youcode.citronix.entities.Farm;
import ma.youcode.citronix.mappers.FarmMapper;
import ma.youcode.citronix.repositories.FarmRepository;
import ma.youcode.citronix.services.interfaces.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmServiceImpl extends GenericServiceImpl<Farm> implements FarmService {

    private final FarmRepository repository;
    private final FarmMapper mapper;

    @Autowired
    public FarmServiceImpl(FarmRepository repository , FarmMapper mapper) {
        super(Farm.class);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public FarmResponseDTO create(FarmCreateDTO createDTO) {

        Farm farm = mapper.fromCreateDTO(createDTO);
        return mapper.toResponseDTO(repository.save(farm));

    }

    @Override
    public FarmResponseDTO update(FarmUpdateDTO updateDTO , Long farmId) {

        if (!isExist(farmId)) {
           throw  new EntityNotFoundException("Farm not found.");
        }
        Farm farm = mapper.fromUpdateDTO(updateDTO);
        farm.setId(farmId);

        return mapper.toResponseDTO(repository.save(farm));
    }

    @Override
    public FarmResponseDTO delete(Long farmId) {
        Farm farm = repository.findById(farmId).orElseThrow(() -> new EntityNotFoundException("Farm not found."));
        repository.delete(farm);
        return mapper.toResponseDTO(farm);
    }

    @Override
    public FarmResponseDTO read(Long farmId) {
        Farm farm = repository.findById(farmId).orElseThrow(() -> new EntityNotFoundException("Farm not found."));
        return mapper.toResponseDTO(farm);
    }

    @Override
    public Page<FarmResponseDTO> readAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Farm> farms = repository.findAll(pageable);

        return farms.map(mapper::toResponseDTO);
    }
}
