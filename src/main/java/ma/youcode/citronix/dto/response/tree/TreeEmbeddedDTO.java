package ma.youcode.citronix.dto.response.tree;

import ma.youcode.citronix.dto.response.HarvestHistory.HarvestHistoryEmbeddedDTO;
import ma.youcode.citronix.entities.HarvestHistory;

import java.time.LocalDate;
import java.util.List;

public record TreeEmbeddedDTO(
        Long id,
        LocalDate plantingDate,
        List<HarvestHistoryEmbeddedDTO> harvestHistories
) {
}
