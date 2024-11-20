package ma.youcode.citronix.services.interfaces;

import ma.youcode.citronix.dto.request.field.FieldCreateDTO;
import ma.youcode.citronix.dto.request.field.FieldUpdateDTO;
import ma.youcode.citronix.dto.response.field.FieldResponseDTO;
import ma.youcode.citronix.entities.Field;
import org.springframework.data.domain.Page;

public interface FieldService {

    FieldResponseDTO create(FieldCreateDTO createDTO);
    FieldResponseDTO update(FieldUpdateDTO updateDTO , Long FieldId);
    FieldResponseDTO delete(Long FieldId);
    FieldResponseDTO read(Long FieldId);
    Page<FieldResponseDTO> readAll(int page , int size );
    Field getFieldById(Long FieldId);





}
