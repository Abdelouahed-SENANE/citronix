package ma.youcode.citronix.mappers;

import ma.senane.utilities.mappers.GenericMapper;
import ma.youcode.citronix.dto.request.harvestHistory.HarvestHistoryCreateDTO;
import ma.youcode.citronix.dto.request.harvestHistory.HarvestHistoryUpdateDTO;
import ma.youcode.citronix.dto.response.HarvestHistory.HarvestHistoryEmbeddedDTO;
import ma.youcode.citronix.dto.response.HarvestHistory.HarvestHistoryResponseDTO;
import ma.youcode.citronix.entities.HarvestHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TreeHarvestMapper extends GenericMapper<HarvestHistory, HarvestHistoryResponseDTO, HarvestHistoryEmbeddedDTO, HarvestHistoryCreateDTO, HarvestHistoryUpdateDTO> {


    @Mapping(source = "harvestId" , target = "treeHarvestId.harvestId")
    @Mapping(source = "treeId" , target = "treeHarvestId.treeId")
    @Mapping(source = "harvestId" , target = "harvest.id")
    @Mapping(source = "treeId" , target = "tree.id")
    HarvestHistory fromCreateDTO(HarvestHistoryCreateDTO createDTO);
}
