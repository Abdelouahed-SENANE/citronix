package ma.youcode.citronix.dto.request.harvest;

import jakarta.validation.constraints.NotNull;
import ma.youcode.citronix.enums.SeasonType;

import java.time.LocalDate;

public record HarvestUpdateDTO(
        @NotNull LocalDate harvestDate,
        @NotNull SeasonType seasonType
) {
}
