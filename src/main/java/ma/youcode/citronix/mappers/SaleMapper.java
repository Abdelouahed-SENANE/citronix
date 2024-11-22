package ma.youcode.citronix.mappers;

import ma.senane.utilities.mappers.GenericMapper;
import ma.youcode.citronix.dto.request.sale.SaleCreateDTO;
import ma.youcode.citronix.dto.request.sale.SaleUpdateDTO;
import ma.youcode.citronix.dto.response.sale.SaleEmbeddedDTO;
import ma.youcode.citronix.dto.response.sale.SaleResponseDTO;
import ma.youcode.citronix.entities.Sale;
import ma.youcode.citronix.entities.Sale;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaleMapper extends GenericMapper<Sale, SaleResponseDTO , SaleEmbeddedDTO , SaleCreateDTO , SaleUpdateDTO> {
}
