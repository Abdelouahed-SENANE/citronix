package ma.youcode.citronix.dto.request.farm;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record FarmUpdateDTO(
        String name,
        String location,
        @Min(2000) Integer surface
) {
    public boolean hasData() {
        return name != null && !name.isEmpty() ||  location != null && !location.isEmpty()  || surface != null  ;
    }
}
