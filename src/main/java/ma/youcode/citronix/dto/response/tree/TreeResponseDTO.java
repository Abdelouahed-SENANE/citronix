package ma.youcode.citronix.dto.response.tree;

import ma.youcode.citronix.dto.response.farm.FarmEmbeddedDTO;
import ma.youcode.citronix.dto.response.field.FieldEmbeddedDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TreeResponseDTO(
    LocalDate plantingDate,
    FieldEmbeddedDTO field

) {
}
