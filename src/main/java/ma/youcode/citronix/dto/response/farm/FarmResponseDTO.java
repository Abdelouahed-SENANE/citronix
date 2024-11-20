package ma.youcode.citronix.dto.response.farm;

import ma.youcode.citronix.dto.response.field.FieldEmbeddedDTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


public record FarmResponseDTO(
        Long id,
        String name,
        String location,
        int surface,
        LocalDateTime creationDate,
        List<FieldEmbeddedDTO> fields

) implements Serializable {
}
