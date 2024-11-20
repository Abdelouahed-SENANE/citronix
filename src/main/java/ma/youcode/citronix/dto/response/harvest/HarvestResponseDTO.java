package ma.youcode.citronix.dto.response.harvest;

import jakarta.validation.constraints.NotNull;
import ma.youcode.citronix.dto.response.field.FieldEmbeddedDTO;
import ma.youcode.citronix.enums.SeasonType;

import java.time.LocalDate;

public record HarvestResponseDTO(
        LocalDate harvestDate,
        SeasonType seasonType

) {
}
