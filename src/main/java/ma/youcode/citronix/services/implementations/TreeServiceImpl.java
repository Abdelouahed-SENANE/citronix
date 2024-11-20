package ma.youcode.citronix.services.implementations;

import lombok.AllArgsConstructor;
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
import java.time.Month;

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
        if (!isPlanting(createDTO.plantingDate())) {
            throw new IllegalStateException("Planting is only allowed between March and May.");
        }

        Tree toTree = mapper.fromCreateDTO(createDTO);
        toTree.setField(field);
        Tree savedTree = repository.save(toTree);

        return mapper.toResponseDTO(repository.save(savedTree));

    }

    @Override
    public TreeResponseDTO update(TreeUpdateDTO updateDTO , Long treeId) {

        Tree tree = repository.findById(treeId).orElseThrow(() -> new TreeNotFoundException("Tree not found."));

        Tree toTree = mapper.fromUpdateDTO(updateDTO);
        toTree.setId(treeId);
        toTree.getUpdatedAt();

        return mapper.toResponseDTO(repository.save(tree));

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

    private boolean isPlanting(LocalDate plantingDate) {
        Month month = plantingDate.getMonth();
        return month == Month.MARCH || month == Month.APRIL || month == Month.MAY;
    }
}
