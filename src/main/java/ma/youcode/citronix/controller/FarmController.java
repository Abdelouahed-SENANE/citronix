package ma.youcode.citronix.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.senane.utilities.dto.SuccessDTO;
import ma.youcode.citronix.dto.request.farm.FarmCreateDTO;
import ma.youcode.citronix.dto.request.farm.FarmUpdateDTO;
import ma.youcode.citronix.dto.response.farm.FarmResponseDTO;
import ma.youcode.citronix.services.interfaces.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static ma.senane.utilities.utils.Response.success;

@RestController
@RequestMapping("/api/farm")
@AllArgsConstructor
public class FarmController {

    private final FarmService service;


    @GetMapping("/get/{id}")
    public ResponseEntity<SuccessDTO> getFarm(@PathVariable Long id) {
        FarmResponseDTO farm = service.read(id);
        return success(200 , "Retrieved." , "farm" , farm);
    }
    @GetMapping("/all")
    public ResponseEntity<SuccessDTO> getFarm(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<FarmResponseDTO> farms = service.readAll(page, size);
        return success(200 , "Retrieved." , "farms" , farms);
    }
    @GetMapping("/filter")
    public ResponseEntity<SuccessDTO> filterFarm(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<FarmResponseDTO> farms = service.readAll(page, size);
        return success(200 , "Retrieved." , "farms" , farms);
    }
    @PostMapping("/add")
    public ResponseEntity<SuccessDTO> addFarm(@Valid @RequestBody FarmCreateDTO createDTO ){
        FarmResponseDTO farm = service.create(createDTO);
        return success(201 , "Created." , "farm" , farm);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SuccessDTO> updateFarm(@PathVariable Long id ,@Valid  @RequestBody FarmUpdateDTO updateDTO ){
        FarmResponseDTO farm = service.update(updateDTO , id);
        return success(200 , "Updated." , "farm" , farm);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SuccessDTO> deleteFarm(@PathVariable Long id){
        FarmResponseDTO farm = service.delete(id);
        return success(200 , "Deleted." , "farm" , farm);
    }


}
