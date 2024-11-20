package ma.youcode.citronix.services.interfaces;

import ma.senane.utilities.generics.interfaces.GenericService;
import ma.youcode.citronix.dto.request.farm.FarmCreateDTO;
import ma.youcode.citronix.dto.request.farm.FarmUpdateDTO;
import ma.youcode.citronix.dto.response.farm.FarmResponseDTO;
import ma.youcode.citronix.entities.Farm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FarmService extends GenericService<Farm> {

    FarmResponseDTO create(FarmCreateDTO createDTO);
    FarmResponseDTO update(FarmUpdateDTO updateDTO , Long farmId);
    FarmResponseDTO delete(Long farmId);
    FarmResponseDTO read(Long farmId);
    Page<FarmResponseDTO> readAll(int page , int size );






}
