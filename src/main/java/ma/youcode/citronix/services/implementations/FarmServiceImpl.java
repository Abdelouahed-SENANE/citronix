package ma.youcode.citronix.services.implementations;

import ma.youcode.citronix.exceptions.farm.FarmNotFoundException;
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
import java.util.Optional;

@Service
@AllArgsConstructor
public class FarmServiceImpl  implements FarmService {

    private final FarmRepository repository;
    private final FarmMapper mapper;


    @Override
    public FarmResponseDTO create(FarmCreateDTO createDTO) {

        Farm farm = mapper.fromCreateDTO(createDTO);
        return mapper.toResponseDTO(repository.save(farm));

    }

    @Override
    public FarmResponseDTO update(FarmUpdateDTO updateDTO , Long farmId) {

        Farm farm = repository.findById(farmId).orElseThrow(() -> new FarmNotFoundException("Farm not found"));


        Farm toFarm = mapper.fromUpdateDTO(updateDTO);
        farm.setId(farmId);

        return mapper.toResponseDTO(repository.save(farm));
    }

    @Override
    public FarmResponseDTO delete(Long farmId) {

        Farm farm = repository.findById(farmId).orElseThrow(() -> new FarmNotFoundException("Farm not found."));
        repository.delete(farm);
        return mapper.toResponseDTO(farm);

    }

    @Override
    public FarmResponseDTO read(Long farmId) {
        Farm farm = repository.findById(farmId).orElseThrow(() -> new FarmNotFoundException("Farm not found."));
        return mapper.toResponseDTO(farm);
    }

    @Override
    public Page<FarmResponseDTO> readAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Farm> farms = repository.findAll(pageable);

        return farms.map(mapper::toResponseDTO);
    }

    public Farm getFarmById(Long farmId) {
        return repository.findById(farmId).orElseThrow(() -> new FarmNotFoundException("Farm not found."));
    }
}
