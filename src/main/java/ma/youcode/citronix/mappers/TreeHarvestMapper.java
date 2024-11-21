package ma.youcode.citronix.mappers;

import ma.senane.utilities.mappers.GenericMapper;
import ma.youcode.citronix.dto.request.treeHarvest.TreeHarvestCreateDTO;
import ma.youcode.citronix.dto.request.treeHarvest.TreeHarvestUpdateDTO;
import ma.youcode.citronix.dto.response.treeHarvest.TreeHarvestEmbeddedDTO;
import ma.youcode.citronix.dto.response.treeHarvest.TreeHarvestResponseDTO;
import ma.youcode.citronix.entities.TreeHarvest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TreeHarvestMapper extends GenericMapper<TreeHarvest, TreeHarvestResponseDTO, TreeHarvestEmbeddedDTO, TreeHarvestCreateDTO, TreeHarvestUpdateDTO> {


    @Mapping(source = "harvestId" , target = "treeHarvestId.harvestId")
    @Mapping(source = "treeId" , target = "treeHarvestId.treeId")
    @Mapping(source = "harvestId" , target = "harvest.id")
    @Mapping(source = "treeId" , target = "tree.id")
    TreeHarvest fromCreateDTO(TreeHarvestCreateDTO createDTO);
}
