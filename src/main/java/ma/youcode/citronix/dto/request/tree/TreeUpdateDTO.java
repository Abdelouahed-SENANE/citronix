package ma.youcode.citronix.dto.request.tree;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TreeUpdateDTO(
        @NotNull LocalDate  plantingDate,
        @NotNull Long fieldId
) {
}
