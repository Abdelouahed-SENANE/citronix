package ma.youcode.citronix.dto.response.tree;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import ma.youcode.citronix.dto.response.field.FieldEmbeddedDTO;
import ma.youcode.citronix.dto.response.HarvestHistory.HarvestHistoryEmbeddedDTO;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TreeResponseDTO(
        Long id,
        LocalDate plantingDate,
        Double productivityAnnual,
        Integer age,
        FieldEmbeddedDTO field,
        List<HarvestHistoryEmbeddedDTO> harvestHistories

) {
}
