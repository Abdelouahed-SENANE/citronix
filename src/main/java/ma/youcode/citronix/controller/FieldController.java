package ma.youcode.citronix.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.senane.utilities.dto.SuccessDTO;
import ma.youcode.citronix.dto.request.field.FieldCreateDTO;
import ma.youcode.citronix.dto.request.field.FieldUpdateDTO;
import ma.youcode.citronix.dto.response.field.FieldResponseDTO;
import ma.youcode.citronix.services.interfaces.FieldService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static ma.senane.utilities.utils.Response.success;

@RestController
@RequestMapping("/api/field")
@AllArgsConstructor
public class FieldController {

    private final FieldService service;

    @GetMapping("/get/{id}")
    public ResponseEntity<SuccessDTO> getField(@PathVariable Long id) {
        FieldResponseDTO field = service.read(id);
        return success(200 , "Retrieved." , "field" , field);
    }
    @GetMapping("/all")
    public ResponseEntity<SuccessDTO> getField(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<FieldResponseDTO> fields = service.readAll(page, size);
        return success(200 , "Retrieved." , "fields" , fields);
    }

    @PostMapping("/add")
    public ResponseEntity<SuccessDTO> addField(@Valid @RequestBody FieldCreateDTO createDTO ){
        FieldResponseDTO field = service.create(createDTO);
        return success(201 , "Created." , "field" , field);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SuccessDTO> updateField(@PathVariable Long id ,@Valid  @RequestBody FieldUpdateDTO updateDTO ){
        FieldResponseDTO field = service.update(updateDTO , id);
        return success(200 , "Updated." , "field" , field);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SuccessDTO> deleteField(@PathVariable Long id){
        FieldResponseDTO field = service.delete(id);
        return success(200 , "Deleted." , "field" , field);
    }


}
