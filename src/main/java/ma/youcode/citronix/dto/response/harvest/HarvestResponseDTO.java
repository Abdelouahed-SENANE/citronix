package ma.youcode.citronix.dto.response.harvest;

import ma.youcode.citronix.dto.response.HarvestHistory.HarvestHistoryEmbeddedDTO;
import ma.youcode.citronix.dto.response.field.FieldEmbeddedDTO;
import ma.youcode.citronix.enums.SeasonType;

import java.time.Year;
import java.util.List;

public record HarvestResponseDTO(
        Long id,
        Year year,
        SeasonType seasonType,
        double quantityTotal,
        FieldEmbeddedDTO field,
        List<HarvestHistoryEmbeddedDTO> harvestHistories
) {
}
