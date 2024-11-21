package ma.youcode.citronix.dto.response.treeHarvest;

import ma.youcode.citronix.dto.response.harvest.HarvestEmbeddedDTO;
import ma.youcode.citronix.dto.response.tree.TreeEmbeddedDTO;

public record TreeHarvestResponseDTO(
        double quantity,
        HarvestEmbeddedDTO harvest,
        TreeEmbeddedDTO tree

) {
}
