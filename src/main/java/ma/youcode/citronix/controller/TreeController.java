package ma.youcode.citronix.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.senane.utilities.dto.SuccessDTO;
import ma.youcode.citronix.dto.request.tree.TreeCreateDTO;
import ma.youcode.citronix.dto.request.tree.TreeUpdateDTO;
import ma.youcode.citronix.dto.response.tree.TreeResponseDTO;
import ma.youcode.citronix.services.interfaces.TreeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static ma.senane.utilities.utils.Response.success;

@RestController
@RequestMapping("/api/tree")
@AllArgsConstructor
public class TreeController {

    private final TreeService service;

    @GetMapping("/get/{id}")
    public ResponseEntity<SuccessDTO> getTree(@PathVariable Long id) {
        TreeResponseDTO tree = service.read(id);
        return success(200 , "Retrieved." , "tree" , tree);
    }
    @GetMapping("/all")
    public ResponseEntity<SuccessDTO> getTree(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<TreeResponseDTO> trees = service.readAll(page, size);
        return success(200 , "Retrieved." , "trees" , trees);
    }

    @PostMapping("/add")
    public ResponseEntity<SuccessDTO> addTree(@Valid @RequestBody TreeCreateDTO createDTO ){
        TreeResponseDTO tree = service.create(createDTO);
        return success(201 , "Created." , "tree" , tree);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SuccessDTO> updateTree(@PathVariable Long id ,@Valid  @RequestBody TreeUpdateDTO updateDTO ){
        TreeResponseDTO tree = service.update(updateDTO , id);
        return success(200 , "Updated." , "tree" , tree);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SuccessDTO> deleteTree(@PathVariable Long id){
        TreeResponseDTO tree = service.delete(id);
        return success(200 , "Deleted." , "tree" , tree);
    }


}
