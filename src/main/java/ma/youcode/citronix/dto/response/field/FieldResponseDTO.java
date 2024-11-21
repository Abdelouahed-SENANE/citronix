package ma.youcode.citronix.dto.response.field;

import ma.youcode.citronix.dto.response.farm.FarmEmbeddedDTO;
import ma.youcode.citronix.dto.response.tree.TreeEmbeddedDTO;

import java.util.List;

public record FieldResponseDTO(
        Long id,
        int surface,
        FarmEmbeddedDTO farm,
        List<TreeEmbeddedDTO> trees
) {
}
