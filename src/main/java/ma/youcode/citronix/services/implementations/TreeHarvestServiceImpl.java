package ma.youcode.citronix.services.implementations;

import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import ma.youcode.citronix.dto.request.treeHarvest.TreeHarvestCreateDTO;
import ma.youcode.citronix.dto.request.treeHarvest.TreeHarvestUpdateDTO;
import ma.youcode.citronix.dto.response.treeHarvest.TreeHarvestResponseDTO;
import ma.youcode.citronix.entities.Field;
import ma.youcode.citronix.entities.Harvest;
import ma.youcode.citronix.entities.Tree;
import ma.youcode.citronix.entities.TreeHarvest;
import ma.youcode.citronix.entities.embedded.TreeHarvestId;
import ma.youcode.citronix.enums.ErrorType;
import ma.youcode.citronix.exceptions.harvest.HarvestNotFoundException;
import ma.youcode.citronix.exceptions.tree.TreeNotFoundException;
import ma.youcode.citronix.exceptions.treeHarvest.TreeHarvestNotFoundException;
import ma.youcode.citronix.mappers.TreeHarvestMapper;
import ma.youcode.citronix.repositories.TreeHarvestRepository;
import ma.youcode.citronix.services.interfaces.FieldService;
import ma.youcode.citronix.services.interfaces.HarvestService;
import ma.youcode.citronix.services.interfaces.TreeHarvestService;
import ma.youcode.citronix.services.interfaces.TreeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TreeHarvestServiceImpl implements TreeHarvestService {

    private final TreeHarvestRepository repository;
    private final TreeHarvestMapper mapper;
    private final TreeService treeService;
    private final HarvestService harvestService;


    @Override
    public TreeHarvestResponseDTO create(TreeHarvestCreateDTO createDTO) {

        Harvest harvest = harvestService.getHarvestById(createDTO.harvestId());
        Tree tree = treeService.getTreeById(createDTO.treeId());
        verifyTreeHarvestable(tree , harvest);

        TreeHarvest toTreeHarvest = mapper.fromCreateDTO(createDTO);
        toTreeHarvest.setHarvest(harvest);
        toTreeHarvest.setTree(tree);
        double quantity = treeService.calculateQuantity(tree);
        toTreeHarvest.setQuantity(quantity);
        TreeHarvest savedTreeHarvest = repository.save(toTreeHarvest);

        return mapper.toResponseDTO(repository.save(savedTreeHarvest));

    }

    @Override
    public TreeHarvestResponseDTO update(TreeHarvestUpdateDTO updateDTO, TreeHarvestId treeHarvestId) {

        TreeHarvest treeHarvest = repository.findById(treeHarvestId).orElseThrow(() -> new TreeHarvestNotFoundException(ErrorType.NOT_FOUND.getMessage("TreeHarvest")));

        TreeHarvest toTreeHarvest = mapper.fromUpdateDTO(updateDTO);

//        toTreeHarvest.setTreeHarvestId(treeHarvestId);
//        toTreeHarvest.setUpdatedAt(LocalDateTime.now());

        return mapper.toResponseDTO(repository.save(toTreeHarvest));

    }

    @Override
    public TreeHarvestResponseDTO delete(TreeHarvestId treeHarvestId) {

        TreeHarvest treeHarvest = repository.findById(treeHarvestId).orElseThrow(() -> new TreeHarvestNotFoundException(ErrorType.NOT_FOUND.getMessage("TreeHarvest")));
        repository.delete(treeHarvest);
        return mapper.toResponseDTO(treeHarvest);

    }

    @Override
    public TreeHarvestResponseDTO read(TreeHarvestId treeHarvestId) {
        TreeHarvest treeHarvest = repository.findById(treeHarvestId).orElseThrow(() -> new TreeHarvestNotFoundException(ErrorType.NOT_FOUND.getMessage("TreeHarvest")));
        return mapper.toResponseDTO(treeHarvest);
    }

    @Override
    public Page<TreeHarvestResponseDTO> readAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<TreeHarvest> treeHarvests = repository.findAll(pageable);

        return treeHarvests.map(mapper::toResponseDTO);
    }

    public TreeHarvest getTreeHarvestById(TreeHarvestId treeHarvestId) {
        return repository.findById(treeHarvestId)
                .orElseThrow(() -> new TreeHarvestNotFoundException(ErrorType.NOT_FOUND.getMessage("TreeHarvest")));
    }

    private void verifyTreeHarvestable(Tree tree ,  Harvest harvest) {
        verifyFieldMatching(tree, harvest);
        verifyDuplicationHarvest(treeHarvestId(tree , harvest));
        verifyTreeProductivity(tree);

    }

    private TreeHarvestId treeHarvestId(Tree tree , Harvest harvest) {
        return new TreeHarvestId(tree.getId(), harvest.getId());
    }

    private void verifyTreeProductivity(Tree tree) {
        boolean isNotProductive = treeService.isNotProductive(tree.getPlantingDate());
        if (isNotProductive) {
            throw new TreeHarvestNotFoundException(ErrorType.NON_PRODUCTIVE_TREE.getMessage());
        }
    }

    private void verifyFieldMatching(Tree tree, Harvest harvest) {
        if (!Objects.equals(harvest.getField().getId(), tree.getField().getId())) {
            throw new IllegalArgumentException(ErrorType.NOT_ON_SAME_FIELD.getMessage());
        }
    }

    private void verifyDuplicationHarvest(TreeHarvestId treeHarvestId) {
        Optional<TreeHarvest> optional = repository.findById(treeHarvestId);

        if (optional.isPresent()) {
            TreeHarvest treeHarvest = optional.get();
            throw new EntityExistsException(
                        ErrorType.DUPLICATE_HARVEST_TREE
                            .getMessage(treeHarvest.getHarvest().getSeasonType().getDisplayName() ,treeHarvest.getHarvest().getYear() )
            );

        }
    }



}
