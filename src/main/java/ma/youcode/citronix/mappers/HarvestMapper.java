package ma.youcode.citronix.mappers;

import ma.senane.utilities.mappers.GenericMapper;
import ma.youcode.citronix.dto.request.harvest.HarvestCreateDTO;
import ma.youcode.citronix.dto.request.harvest.HarvestUpdateDTO;
import ma.youcode.citronix.dto.response.harvest.HarvestEmbeddedDTO;
import ma.youcode.citronix.dto.response.harvest.HarvestResponseDTO;
import ma.youcode.citronix.entities.Harvest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HarvestMapper extends GenericMapper<Harvest, HarvestResponseDTO, HarvestEmbeddedDTO, HarvestCreateDTO, HarvestUpdateDTO> {

    @Mapping(source = "fieldId" , target = "field.id")
    Harvest fromCreateDTO(HarvestCreateDTO harvestCreateDTO);
}
