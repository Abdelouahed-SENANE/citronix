package ma.youcode.citronix.services.interfaces;

import ma.youcode.citronix.dto.request.tree.TreeCreateDTO;
import ma.youcode.citronix.dto.request.tree.TreeUpdateDTO;
import ma.youcode.citronix.dto.response.tree.TreeResponseDTO;
import ma.youcode.citronix.entities.Tree;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.Period;

public interface TreeService {

    TreeResponseDTO create(TreeCreateDTO createDTO);
    TreeResponseDTO update(TreeUpdateDTO updateDTO , Long TreeId);
    TreeResponseDTO delete(Long TreeId);
    TreeResponseDTO read(Long TreeId);
    Page<TreeResponseDTO> readAll(int page , int size );
    Tree getTreeById(Long TreeId);
    boolean isNotProductive(LocalDate plantingDate);
    double calculateQuantity(Tree tree);
}
