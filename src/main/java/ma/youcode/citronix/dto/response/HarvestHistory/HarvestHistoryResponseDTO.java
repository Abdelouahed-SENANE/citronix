package ma.youcode.citronix.dto.response.HarvestHistory;

import ma.youcode.citronix.dto.response.harvest.HarvestEmbeddedDTO;
import ma.youcode.citronix.dto.response.tree.TreeEmbeddedDTO;

import java.time.LocalDateTime;

public record HarvestHistoryResponseDTO(
        double quantity,
        LocalDateTime harvestedAt,
        HarvestEmbeddedDTO harvest,
        TreeEmbeddedDTO tree

) {
}
