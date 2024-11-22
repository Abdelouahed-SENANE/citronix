package ma.youcode.citronix.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.senane.utilities.dto.SuccessDTO;
import ma.youcode.citronix.dto.request.harvestHistory.HarvestHistoryCreateDTO;
import ma.youcode.citronix.dto.request.harvestHistory.HarvestHistoryUpdateDTO;
import ma.youcode.citronix.dto.response.HarvestHistory.HarvestHistoryResponseDTO;
import ma.youcode.citronix.entities.embedded.TreeHarvestId;
import ma.youcode.citronix.services.interfaces.TreeHarvestService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static ma.senane.utilities.utils.Response.success;

@RestController
@RequestMapping("/api/harvest-history")
@AllArgsConstructor
public class HarvestHistoryController {

    private final TreeHarvestService service;

    @GetMapping("/get/{harvestId}/{treeId}")
    public ResponseEntity<SuccessDTO> getTreeHarvest(@PathVariable Long harvestId , @PathVariable Long treeId) {
        TreeHarvestId treeHarvestId = new TreeHarvestId(harvestId, treeId);
        HarvestHistoryResponseDTO history = service.read(treeHarvestId);
        return success(200 , "Retrieved." , "history" , history);

    }
    @GetMapping("/all")
    public ResponseEntity<SuccessDTO> getTreeHarvest(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<HarvestHistoryResponseDTO> histories = service.readAll(page, size);
        return success(200 , "Retrieved." , "histories" , histories);
    }

    @PostMapping("/add")
    public ResponseEntity<SuccessDTO> addTreeHarvest(@Valid @RequestBody HarvestHistoryCreateDTO createDTO ){
        HarvestHistoryResponseDTO hsitory = service.create(createDTO);
        return success(201 , "Created." , "history" , hsitory);
    }

    @PutMapping("/update/{harvestId}/{treeId}")
    public ResponseEntity<SuccessDTO> updateTreeHarvest(@PathVariable Long harvestId , @PathVariable Long treeId ,@Valid  @RequestBody HarvestHistoryUpdateDTO updateDTO ){

        TreeHarvestId treeHarvestId = new TreeHarvestId(harvestId, treeId);
        HarvestHistoryResponseDTO hsitory = service.update(updateDTO , treeHarvestId);
        return success(200 , "Updated." , "history" , hsitory);

    }

    @DeleteMapping("/delete/{harvestId}/{treeId}")
    public ResponseEntity<SuccessDTO> deleteTreeHarvest(@PathVariable Long harvestId , @PathVariable Long treeId ){
        TreeHarvestId treeHarvestId = new TreeHarvestId(harvestId, treeId);
        HarvestHistoryResponseDTO hsitory = service.delete(treeHarvestId);
        return success(200 , "Deleted." , "history" , hsitory);
    }


}
