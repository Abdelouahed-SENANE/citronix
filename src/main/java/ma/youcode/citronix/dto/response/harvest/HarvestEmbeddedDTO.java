package ma.youcode.citronix.dto.response.harvest;

import jakarta.validation.constraints.NotNull;
import ma.youcode.citronix.enums.SeasonType;

import java.time.LocalDate;
import java.time.Year;

public record HarvestEmbeddedDTO(
        Long id,
        Year year,
        SeasonType season
) {
}
