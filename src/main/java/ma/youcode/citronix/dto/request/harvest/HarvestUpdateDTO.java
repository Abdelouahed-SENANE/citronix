package ma.youcode.citronix.dto.request.harvest;

import jakarta.validation.constraints.NotNull;
import ma.youcode.citronix.enums.SeasonType;

import java.time.LocalDate;
import java.time.Year;

public record HarvestUpdateDTO(
        @NotNull Year year,
        @NotNull SeasonType seasonType,
        @NotNull Long fieldId
) {
}
