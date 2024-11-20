package ma.youcode.citronix.dto.request.field;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record FieldCreateDTO(
        @NotNull @Min(1000) Integer surface,
        @NotNull Long farmId
        ) implements Serializable {

}
