package ma.youcode.citronix.dto.response.tree;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TreeEmbeddedDTO(
        LocalDate plantingDate
) {
}
