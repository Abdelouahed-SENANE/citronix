package ma.youcode.citronix.dto.request.sale;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SaleUpdateDTO(
        @NotNull Double quantity,
        @NotNull Double unitPrice,
        @NotEmpty String clientName,
        @NotNull Long harvestId
) {
}
