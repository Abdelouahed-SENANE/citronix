package ma.youcode.citronix.dto.request.farm;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record FarmCreateDTO(
        @NotEmpty String name,
        @NotEmpty String location,
        @NotNull @Min(2000) Integer surface
        ) implements Serializable {

}
