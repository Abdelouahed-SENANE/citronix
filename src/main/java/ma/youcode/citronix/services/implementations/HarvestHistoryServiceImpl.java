package ma.youcode.citronix.services.implementations;

import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import ma.youcode.citronix.dto.request.harvestHistory.HarvestHistoryCreateDTO;
import ma.youcode.citronix.dto.request.harvestHistory.HarvestHistoryUpdateDTO;
import ma.youcode.citronix.dto.response.HarvestHistory.HarvestHistoryResponseDTO;
import ma.youcode.citronix.entities.Harvest;
import ma.youcode.citronix.entities.HarvestHistory;
import ma.youcode.citronix.entities.Tree;
import ma.youcode.citronix.entities.embedded.TreeHarvestId;
import ma.youcode.citronix.enums.ErrorType;
import ma.youcode.citronix.exceptions.treeHarvest.TreeHarvestNotFoundException;
import ma.youcode.citronix.mappers.TreeHarvestMapper;
import ma.youcode.citronix.repositories.HarvestHistoryRepository;
import ma.youcode.citronix.services.interfaces.HarvestService;
import ma.youcode.citronix.services.interfaces.TreeHarvestService;
import ma.youcode.citronix.services.interfaces.TreeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HarvestHistoryServiceImpl implements TreeHarvestService {

    private final HarvestHistoryRepository repository;
    private final TreeHarvestMapper mapper;
    private final TreeService treeService;
    private final HarvestService harvestService;


    @Override
    public HarvestHistoryResponseDTO create(HarvestHistoryCreateDTO createDTO) {

        Harvest harvest = harvestService.getHarvestById(createDTO.harvestId());
        Tree tree = treeService.getTreeById(createDTO.treeId());
        verifyTreeHarvestable(tree , harvest);

        HarvestHistory toHarvestHistory = mapper.fromCreateDTO(createDTO);
        toHarvestHistory.setHarvest(harvest);
        toHarvestHistory.setTree(tree);
        HarvestHistory savedHarvestHistory = repository.save(toHarvestHistory);

        return mapper.toResponseDTO(repository.save(savedHarvestHistory));

    }

    @Override
    public HarvestHistoryResponseDTO update(HarvestHistoryUpdateDTO updateDTO, TreeHarvestId treeHarvestId) {

        HarvestHistory harvestHistory = repository.findById(treeHarvestId).orElseThrow(() -> new TreeHarvestNotFoundException(ErrorType.NOT_FOUND.getMessage("Harvest history")));

        HarvestHistory toHarvestHistory = mapper.fromUpdateDTO(updateDTO);

//        toTreeHarvest.setTreeHarvestId(treeHarvestId);
//        toTreeHarvest.setUpdatedAt(LocalDateTime.now());

        return mapper.toResponseDTO(repository.save(toHarvestHistory));

    }

    @Override
    public HarvestHistoryResponseDTO delete(TreeHarvestId treeHarvestId) {

        HarvestHistory harvestHistory = repository.findById(treeHarvestId).orElseThrow(() -> new TreeHarvestNotFoundException(ErrorType.NOT_FOUND.getMessage("Harvest history")));
        repository.delete(harvestHistory);
        return mapper.toResponseDTO(harvestHistory);

    }

    @Override
    public HarvestHistoryResponseDTO read(TreeHarvestId treeHarvestId) {
        HarvestHistory harvestHistory = repository.findById(treeHarvestId).orElseThrow(() -> new TreeHarvestNotFoundException(ErrorType.NOT_FOUND.getMessage("Harvest history")));
        return mapper.toResponseDTO(harvestHistory);
    }

    @Override
    public Page<HarvestHistoryResponseDTO> readAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<HarvestHistory> treeHarvests = repository.findAll(pageable);

        return treeHarvests.map(mapper::toResponseDTO);
    }

    public HarvestHistory getTreeHarvestById(TreeHarvestId treeHarvestId) {
        return repository.findById(treeHarvestId)
                .orElseThrow(() -> new TreeHarvestNotFoundException(ErrorType.NOT_FOUND.getMessage("Harvest history")));
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
        Optional<HarvestHistory> optional = repository.findById(treeHarvestId);

        if (optional.isPresent()) {
            HarvestHistory harvestHistory = optional.get();
            throw new EntityExistsException(
                        ErrorType.DUPLICATE_HARVEST_TREE
                            .getMessage(harvestHistory.getHarvest().getSeasonType().getDisplayName() , harvestHistory.getHarvest().getYear() )
            );

        }
    }



}
