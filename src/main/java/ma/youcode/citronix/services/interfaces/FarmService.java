package ma.youcode.citronix.services.interfaces;

import ma.youcode.citronix.dto.request.farm.FarmCreateDTO;
import ma.youcode.citronix.dto.request.farm.FarmUpdateDTO;
import ma.youcode.citronix.dto.request.farm.FilterFarmDTO;
import ma.youcode.citronix.dto.response.farm.FarmResponseDTO;
import ma.youcode.citronix.entities.Farm;
import org.springframework.data.domain.Page;

public interface FarmService {

    FarmResponseDTO create(FarmCreateDTO createDTO);
    FarmResponseDTO update(FarmUpdateDTO updateDTO , Long farmId);
    FarmResponseDTO delete(Long farmId);
    FarmResponseDTO read(Long farmId);
    Page<FarmResponseDTO> readAll(int page , int size );
    Farm getFarmById(Long farmId);
    Page<FarmResponseDTO> filter(FilterFarmDTO filterDTO, int page , int size);

}
