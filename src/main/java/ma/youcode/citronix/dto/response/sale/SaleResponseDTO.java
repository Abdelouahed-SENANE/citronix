package ma.youcode.citronix.dto.response.sale;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import ma.youcode.citronix.dto.response.harvest.HarvestEmbeddedDTO;
import ma.youcode.citronix.entities.Harvest;

import java.time.LocalDate;

public record SaleResponseDTO(
        Long id,
        Double quantity,
        Double unitPrice,
        String clientName,
        double totalPrice,
        HarvestEmbeddedDTO harvest
) {
}
