package ma.youcode.citronix.dto.response.field;

import ma.youcode.citronix.dto.response.farm.FarmEmbeddedDTO;

public record FieldResponseDTO(
    int surface,
    FarmEmbeddedDTO farm
) {
}
