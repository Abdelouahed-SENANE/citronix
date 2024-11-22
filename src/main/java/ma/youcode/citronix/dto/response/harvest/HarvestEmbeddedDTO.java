package ma.youcode.citronix.dto.response.harvest;


import ma.youcode.citronix.enums.SeasonType;
import java.time.Year;

public record HarvestEmbeddedDTO(
        Long id,
        Year year,
        SeasonType seasonType,
        double quantityTotal
) {
}
