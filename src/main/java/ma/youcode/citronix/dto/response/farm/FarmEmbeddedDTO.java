package ma.youcode.citronix.dto.response.farm;

import java.time.LocalDateTime;

public record FarmEmbeddedDTO(
        Long id,
        String name,
        String location,
        int surface,
        LocalDateTime creationDate
) {
}
