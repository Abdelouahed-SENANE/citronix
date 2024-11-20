package ma.youcode.citronix.mappers;

import ma.senane.utilities.mappers.GenericMapper;
import ma.youcode.citronix.dto.request.tree.TreeCreateDTO;
import ma.youcode.citronix.dto.request.tree.TreeUpdateDTO;
import ma.youcode.citronix.dto.response.tree.TreeEmbeddedDTO;
import ma.youcode.citronix.dto.response.tree.TreeResponseDTO;
import ma.youcode.citronix.entities.Tree;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TreeMapper extends GenericMapper<Tree, TreeResponseDTO, TreeEmbeddedDTO, TreeCreateDTO, TreeUpdateDTO> {
}
