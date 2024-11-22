package ma.youcode.citronix.services.interfaces;

import ma.youcode.citronix.dto.request.harvestHistory.HarvestHistoryCreateDTO;
import ma.youcode.citronix.dto.request.harvestHistory.HarvestHistoryUpdateDTO;
import ma.youcode.citronix.dto.response.HarvestHistory.HarvestHistoryResponseDTO;
import ma.youcode.citronix.entities.HarvestHistory;
import ma.youcode.citronix.entities.embedded.TreeHarvestId;
import org.springframework.data.domain.Page;

public interface TreeHarvestService {

    HarvestHistoryResponseDTO create(HarvestHistoryCreateDTO createDTO);
    HarvestHistoryResponseDTO update(HarvestHistoryUpdateDTO updateDTO , TreeHarvestId TreeHarvestId);
    HarvestHistoryResponseDTO delete(TreeHarvestId TreeHarvestId);
    HarvestHistoryResponseDTO read(TreeHarvestId TreeHarvestId);
    Page<HarvestHistoryResponseDTO> readAll(int page , int size );
    HarvestHistory getTreeHarvestById(TreeHarvestId TreeHarvestId);




}
