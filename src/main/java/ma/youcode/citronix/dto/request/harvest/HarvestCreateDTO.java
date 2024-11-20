package ma.youcode.citronix.dto.request.harvest;

import jakarta.validation.constraints.NotNull;
import ma.youcode.citronix.enums.SeasonType;

import java.io.Serializable;
import java.time.LocalDate;

public record HarvestCreateDTO(
        @NotNull LocalDate harvestDate,
        @NotNull SeasonType seasonType
        ) implements Serializable {

}
