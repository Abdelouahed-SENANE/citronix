package ma.youcode.citronix.dto.request.harvest;

import jakarta.validation.constraints.NotNull;
import ma.youcode.citronix.enums.SeasonType;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;

public record HarvestCreateDTO(
        @NotNull Year year,
        @NotNull SeasonType seasonType,
        @NotNull Long fieldId
        ) implements Serializable {

}
