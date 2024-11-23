package ma.youcode.citronix.services.implementations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.youcode.citronix.dto.request.tree.TreeCreateDTO;
import ma.youcode.citronix.dto.request.tree.TreeUpdateDTO;
import ma.youcode.citronix.dto.response.tree.TreeResponseDTO;
import ma.youcode.citronix.entities.Field;
import ma.youcode.citronix.entities.Tree;
import ma.youcode.citronix.exceptions.tree.TreeNotFoundException;
import ma.youcode.citronix.mappers.TreeMapper;
import ma.youcode.citronix.repositories.TreeRepository;
import ma.youcode.citronix.services.interfaces.FieldService;
import ma.youcode.citronix.services.interfaces.TreeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;

@Slf4j
@Service
@AllArgsConstructor
public class TreeServiceImpl implements TreeService {

    private final TreeRepository repository;
    private final TreeMapper mapper;
    private final FieldService fieldService;


    @Override
    public TreeResponseDTO create(TreeCreateDTO createDTO) {

        Field field = fieldService.getFieldById(createDTO.fieldId());
        if (!fieldService.canAddTree(field)) {
            throw new IllegalStateException("Maximum tree density reached. No more trees can be added to this field.");
        }
        if (isNotPlanting(createDTO.plantingDate())) {
            throw new IllegalArgumentException("Planting is only allowed between March and May.");
        }

        Tree toTree = mapper.fromCreateDTO(createDTO);
        toTree.setField(field);
        Tree savedTree = repository.save(toTree);

        return mapper.toResponseDTO(repository.save(savedTree));

    }

    @Override
    public TreeResponseDTO update(TreeUpdateDTO updateDTO , Long treeId) {

        Tree tree = repository.findById(treeId).orElseThrow(() -> new TreeNotFoundException("Tree not found."));

        if (isNotPlanting(updateDTO.plantingDate())) {
            throw new IllegalArgumentException("Planting is only allowed between March and May.");
        }
        Tree toTree = mapper.fromUpdateDTO(updateDTO);

        toTree.setId(treeId);
        toTree.setUpdatedAt(LocalDateTime.now());
        toTree.setField(tree.getField());

        return mapper.toResponseDTO(repository.save(toTree));

    }

    @Override
    public TreeResponseDTO delete(Long treeId) {

        Tree tree = repository.findById(treeId).orElseThrow(() -> new TreeNotFoundException("Tree not found."));
        repository.delete(tree);
        return mapper.toResponseDTO(tree);

    }

    @Override
    public TreeResponseDTO read(Long treeId) {
        Tree tree = repository.findById(treeId).orElseThrow(() -> new TreeNotFoundException("Tree not found."));
        return mapper.toResponseDTO(tree);
    }

    @Override
    public Page<TreeResponseDTO> readAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Tree> trees = repository.findAll(pageable);

        return trees.map(mapper::toResponseDTO);
    }

    public Tree getTreeById(Long treeId) {
        return repository.findById(treeId)
                .orElseThrow(() -> new TreeNotFoundException("Tree not found."));
    }

    private boolean isNotPlanting(LocalDate plantingDate) {
        Month month = plantingDate.getMonth();
        return month != Month.MARCH && month != Month.APRIL && month != Month.MAY;
    }

    @Override
    public boolean isNotProductive(LocalDate plantingDate) {
        int age =  Period.between(plantingDate, LocalDate.now()).getYears();
        return age > 20;
    }

    @Override
    public TreeResponseDTO getQteAnnualPerTree(Long treeId) {

        Tree tree = repository.findById(treeId).orElseThrow(() -> new TreeNotFoundException("Tree not found."));
        updateTreeDetail(tree);
        return mapper.toResponseDTO(tree);

    }

    private void updateTreeDetail(Tree tree) {

        Integer treeAge = calculateAge(tree.getPlantingDate());
        Double productivityAnnul = calculateAnnualQtePerTree(tree);
        tree.setAge(treeAge);
        tree.setProductivityAnnual(productivityAnnul);

    }
    private double calculateAnnualQtePerTree(Tree tree) {

        int age = calculateAge(tree.getPlantingDate());
        if (age > 20) {
            return 0.0;
        }
        if (age < 3) {
            return  2.5 * 4;
        } else if (age <= 10) {
            return  12 * 4;
        }
        return 20 * 4;

    }

    private int calculateAge(LocalDate plantingDate) {
        return Period.between(plantingDate, LocalDate.now()).getYears();
    }
}
