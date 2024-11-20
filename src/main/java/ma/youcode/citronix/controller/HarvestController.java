package ma.youcode.citronix.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.senane.utilities.dto.SuccessDTO;
import ma.youcode.citronix.dto.request.harvest.HarvestCreateDTO;
import ma.youcode.citronix.dto.request.harvest.HarvestUpdateDTO;
import ma.youcode.citronix.dto.response.harvest.HarvestResponseDTO;
import ma.youcode.citronix.services.interfaces.HarvestService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static ma.senane.utilities.utils.Response.success;

@RestController
@RequestMapping("/api/harvest")
@AllArgsConstructor
public class HarvestController {

    private final HarvestService service;

    @GetMapping("/get/{id}")
    public ResponseEntity<SuccessDTO> getHarvest(@PathVariable Long id) {
        HarvestResponseDTO harvest = service.read(id);
        return success(200 , "Retrieved." , "harvest" , harvest);
    }
    @GetMapping("/all")
    public ResponseEntity<SuccessDTO> getHarvest(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<HarvestResponseDTO> harvests = service.readAll(page, size);
        return success(200 , "Retrieved." , "harvests" , harvests);
    }

    @PostMapping("/add")
    public ResponseEntity<SuccessDTO> addHarvest(@Valid @RequestBody HarvestCreateDTO createDTO ){
        HarvestResponseDTO harvest = service.create(createDTO);
        return success(201 , "Created." , "harvest" , harvest);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SuccessDTO> updateHarvest(@PathVariable Long id ,@Valid  @RequestBody HarvestUpdateDTO updateDTO ){
        HarvestResponseDTO harvest = service.update(updateDTO , id);
        return success(200 , "Updated." , "harvest" , harvest);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SuccessDTO> deleteHarvest(@PathVariable Long id){
        HarvestResponseDTO harvest = service.delete(id);
        return success(200 , "Deleted." , "harvest" , harvest);
    }


}
