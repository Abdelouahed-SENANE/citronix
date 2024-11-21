package ma.youcode.citronix.services.interfaces;

import ma.youcode.citronix.dto.request.treeHarvest.TreeHarvestCreateDTO;
import ma.youcode.citronix.dto.request.treeHarvest.TreeHarvestUpdateDTO;
import ma.youcode.citronix.dto.response.treeHarvest.TreeHarvestResponseDTO;
import ma.youcode.citronix.entities.TreeHarvest;
import ma.youcode.citronix.entities.embedded.TreeHarvestId;
import org.springframework.data.domain.Page;

public interface TreeHarvestService {

    TreeHarvestResponseDTO create(TreeHarvestCreateDTO createDTO);
    TreeHarvestResponseDTO update(TreeHarvestUpdateDTO updateDTO , TreeHarvestId TreeHarvestId);
    TreeHarvestResponseDTO delete(TreeHarvestId TreeHarvestId);
    TreeHarvestResponseDTO read(TreeHarvestId TreeHarvestId);
    Page<TreeHarvestResponseDTO> readAll(int page , int size );
    TreeHarvest getTreeHarvestById(TreeHarvestId TreeHarvestId);




}
