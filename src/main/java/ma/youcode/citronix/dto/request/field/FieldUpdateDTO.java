package ma.youcode.citronix.dto.request.field;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record FieldUpdateDTO(
        @Min(1000) Integer surface,
        Long farmId
) {
    public boolean hasData() {
        return surface != null || farmId != null;
    }
}
