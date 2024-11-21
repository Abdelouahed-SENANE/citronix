package ma.youcode.citronix.dto.response.harvest;

import ma.youcode.citronix.dto.response.treeHarvest.TreeHarvestEmbeddedDTO;
import ma.youcode.citronix.enums.SeasonType;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

public record HarvestResponseDTO(
        Long id,
        Year year,
        SeasonType season,
        List<TreeHarvestEmbeddedDTO> treeHarvests
) {
}
