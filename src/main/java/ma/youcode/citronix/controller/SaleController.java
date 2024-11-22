package ma.youcode.citronix.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.senane.utilities.dto.SuccessDTO;
import ma.youcode.citronix.dto.request.sale.SaleCreateDTO;
import ma.youcode.citronix.dto.request.sale.SaleUpdateDTO;
import ma.youcode.citronix.dto.response.sale.SaleResponseDTO;
import ma.youcode.citronix.services.interfaces.SaleService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static ma.senane.utilities.utils.Response.success;

@RestController
@RequestMapping("/api/sale")
@AllArgsConstructor
public class SaleController {

    private final SaleService service;


    @GetMapping("/get/{id}")
    public ResponseEntity<SuccessDTO> getSale(@PathVariable Long id) {
        SaleResponseDTO sale = service.read(id);
        return success(200 , "Retrieved." , "sale" , sale);
    }
    @GetMapping("/all")
    public ResponseEntity<SuccessDTO> getSale(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<SaleResponseDTO> sales = service.readAll(page, size);
        return success(200 , "Retrieved." , "sales" , sales);
    }

    @PostMapping("/add")
    public ResponseEntity<SuccessDTO> addSale(@Valid @RequestBody SaleCreateDTO createDTO ){
        SaleResponseDTO sale = service.create(createDTO);
        return success(201 , "Created." , "sale" , sale);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SuccessDTO> updateSale(@PathVariable Long id ,@Valid  @RequestBody SaleUpdateDTO updateDTO ){
        SaleResponseDTO sale = service.update(updateDTO , id);
        return success(200 , "Updated." , "sale" , sale);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SuccessDTO> deleteSale(@PathVariable Long id){
        SaleResponseDTO sale = service.delete(id);
        return success(200 , "Deleted." , "sale" , sale);
    }


}
