package ma.youcode.citronix.services.interfaces;

import ma.youcode.citronix.dto.request.harvest.HarvestCreateDTO;
import ma.youcode.citronix.dto.request.harvest.HarvestUpdateDTO;
import ma.youcode.citronix.dto.response.harvest.HarvestResponseDTO;
import ma.youcode.citronix.entities.Harvest;
import org.springframework.data.domain.Page;

public interface HarvestService {

    HarvestResponseDTO create(HarvestCreateDTO createDTO);
    HarvestResponseDTO update(HarvestUpdateDTO updateDTO , Long HarvestId);
    HarvestResponseDTO delete(Long HarvestId);
    HarvestResponseDTO read(Long HarvestId);
    Page<HarvestResponseDTO> readAll(int page , int size );
    Harvest getHarvestById(Long HarvestId);




}
