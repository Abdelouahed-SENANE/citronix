package ma.youcode.citronix.dto.response.field;

import ma.youcode.citronix.dto.response.tree.TreeEmbeddedDTO;

import java.util.List;

public record FieldEmbeddedDTO(
        Long id,
        int surface
//        List<TreeEmbeddedDTO> trees

) {
}
