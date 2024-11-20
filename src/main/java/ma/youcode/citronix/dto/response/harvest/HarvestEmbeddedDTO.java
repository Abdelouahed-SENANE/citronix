package ma.youcode.citronix.dto.response.harvest;

import jakarta.validation.constraints.NotNull;
import ma.youcode.citronix.enums.SeasonType;

import java.time.LocalDate;

public record HarvestEmbeddedDTO(
        LocalDate harvestDate,
        SeasonType seasonType
) {
}
