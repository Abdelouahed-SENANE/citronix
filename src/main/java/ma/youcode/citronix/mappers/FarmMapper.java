package ma.youcode.citronix.mappers;

import ma.senane.utilities.mappers.GenericMapper;
import ma.youcode.citronix.dto.request.farm.FarmCreateDTO;
import ma.youcode.citronix.dto.request.farm.FarmUpdateDTO;
import ma.youcode.citronix.dto.response.farm.FarmEmbeddedDTO;
import ma.youcode.citronix.dto.response.farm.FarmResponseDTO;
import ma.youcode.citronix.entities.Farm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FarmMapper extends GenericMapper<Farm , FarmResponseDTO , FarmEmbeddedDTO , FarmCreateDTO , FarmUpdateDTO> {
}
