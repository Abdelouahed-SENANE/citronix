package ma.youcode.citronix.services.interfaces;

import ma.youcode.citronix.dto.request.sale.SaleCreateDTO;
import ma.youcode.citronix.dto.request.sale.SaleUpdateDTO;
import ma.youcode.citronix.dto.response.sale.SaleResponseDTO;
import ma.youcode.citronix.entities.Sale;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface SaleService {

    SaleResponseDTO create(SaleCreateDTO createDTO);
    SaleResponseDTO update(SaleUpdateDTO updateDTO , Long SaleId);
    SaleResponseDTO delete(Long SaleId);
    SaleResponseDTO read(Long SaleId);
    Page<SaleResponseDTO> readAll(int page , int size );

}
