package ma.youcode.citronix.dto.response.HarvestHistory;

import java.time.LocalDateTime;

public record HarvestHistoryEmbeddedDTO(
        double quantity,
        LocalDateTime harvestedAt
) {
}
