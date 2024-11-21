package ma.youcode.citronix.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.senane.utilities.dto.SuccessDTO;
import ma.youcode.citronix.dto.request.treeHarvest.TreeHarvestCreateDTO;
import ma.youcode.citronix.dto.request.treeHarvest.TreeHarvestUpdateDTO;
import ma.youcode.citronix.dto.response.treeHarvest.TreeHarvestResponseDTO;
import ma.youcode.citronix.entities.embedded.TreeHarvestId;
import ma.youcode.citronix.services.interfaces.TreeHarvestService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static ma.senane.utilities.utils.Response.success;

@RestController
@RequestMapping("/api/tree-harvest")
@AllArgsConstructor
public class TreeHarvestController {

    private final TreeHarvestService service;

    @GetMapping("/get/{harvestId}/{treeId}")
    public ResponseEntity<SuccessDTO> getTreeHarvest(@PathVariable Long harvestId , @PathVariable Long treeId) {
        TreeHarvestId treeHarvestId = new TreeHarvestId(harvestId, treeId);
        TreeHarvestResponseDTO treeHarvest = service.read(treeHarvestId);
        return success(200 , "Retrieved." , "treeHarvest" , treeHarvest);

    }
    @GetMapping("/all")
    public ResponseEntity<SuccessDTO> getTreeHarvest(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<TreeHarvestResponseDTO> treeHarvests = service.readAll(page, size);
        return success(200 , "Retrieved." , "treeHarvests" , treeHarvests);
    }

    @PostMapping("/add")
    public ResponseEntity<SuccessDTO> addTreeHarvest(@Valid @RequestBody TreeHarvestCreateDTO createDTO ){
        TreeHarvestResponseDTO treeHarvest = service.create(createDTO);
        return success(201 , "Created." , "treeHarvest" , treeHarvest);
    }

    @PutMapping("/update/{harvestId}/{treeId}")
    public ResponseEntity<SuccessDTO> updateTreeHarvest(@PathVariable Long harvestId , @PathVariable Long treeId ,@Valid  @RequestBody TreeHarvestUpdateDTO updateDTO ){

        TreeHarvestId treeHarvestId = new TreeHarvestId(harvestId, treeId);
        TreeHarvestResponseDTO treeHarvest = service.update(updateDTO , treeHarvestId);
        return success(200 , "Updated." , "treeHarvest" , treeHarvest);

    }

    @DeleteMapping("/delete/{harvestId}/{treeId}")
    public ResponseEntity<SuccessDTO> deleteTreeHarvest(@PathVariable Long harvestId , @PathVariable Long treeId ){
        TreeHarvestId treeHarvestId = new TreeHarvestId(harvestId, treeId);
        TreeHarvestResponseDTO treeHarvest = service.delete(treeHarvestId);
        return success(200 , "Deleted." , "treeHarvest" , treeHarvest);
    }


}
