package ma.youcode.citronix.dto.response.tree;

import ma.youcode.citronix.dto.response.field.FieldEmbeddedDTO;
import ma.youcode.citronix.dto.response.treeHarvest.TreeHarvestEmbeddedDTO;

import java.time.LocalDate;
import java.util.List;

public record TreeResponseDTO(
        LocalDate plantingDate,
        FieldEmbeddedDTO field,
        List<TreeHarvestEmbeddedDTO> treeHarvests


) {
}
