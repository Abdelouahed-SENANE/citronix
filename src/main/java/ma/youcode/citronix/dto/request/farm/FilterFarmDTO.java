package ma.youcode.citronix.dto.request.farm;

import java.time.LocalDate;

public record FilterFarmDTO(
        String name,
        LocalDate creationDate,
        String location,
        Integer surface
) {
}
