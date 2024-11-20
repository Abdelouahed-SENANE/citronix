package ma.youcode.citronix.dto.request.farm;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record FarmUpdateDTO(
        @NotEmpty String name,
        @NotEmpty String location,
        @NotNull @Min(2000) Integer surface
) {
}
