package ma.youcode.citronix.dto.response.farm;

import java.io.Serializable;
import java.time.LocalDateTime;

public record FarmResponseDTO(
        Long id,
        String name,
        String location,
        int surface,
        LocalDateTime createdAt
) implements Serializable {
}
