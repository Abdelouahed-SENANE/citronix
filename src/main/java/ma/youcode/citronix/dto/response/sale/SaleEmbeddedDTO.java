package ma.youcode.citronix.dto.response.sale;

import ma.youcode.citronix.dto.response.harvest.HarvestEmbeddedDTO;

import java.time.LocalDate;

public record SaleEmbeddedDTO(
        Long id,
        Double quantity,
        Double unitPrice,
        String clientName,
        double totalPrice,
        HarvestEmbeddedDTO harvest
) {
}
