package ma.youcode.citronix.services.implementations;

import lombok.AllArgsConstructor;
import ma.youcode.citronix.dto.request.sale.SaleCreateDTO;
import ma.youcode.citronix.dto.request.sale.SaleUpdateDTO;
import ma.youcode.citronix.dto.response.sale.SaleResponseDTO;
import ma.youcode.citronix.entities.Harvest;
import ma.youcode.citronix.entities.Sale;
import ma.youcode.citronix.enums.ErrorType;
import ma.youcode.citronix.exceptions.sale.SaleNotFoundException;
import ma.youcode.citronix.mappers.SaleMapper;
import ma.youcode.citronix.repositories.SaleRepository;
import ma.youcode.citronix.services.interfaces.HarvestService;
import ma.youcode.citronix.services.interfaces.SaleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository repository;
    private final SaleMapper mapper;
    private final HarvestService harvestService;

    @Override
    public SaleResponseDTO create(SaleCreateDTO createDTO) {

        Harvest harvest = harvestService.getHarvestById(createDTO.harvestId());
        verifyQuantityExist(harvest, createDTO.quantity());

        Sale sale = mapper.fromCreateDTO(createDTO);
        sale.setHarvest(harvest);
        Sale savedSale = repository.save(sale);
        savedSale.setTotalPrice(calculateCost(savedSale.getQuantity() , savedSale.getUnitPrice()));
        return mapper.toResponseDTO(savedSale);

    }

    @Override
    public SaleResponseDTO update(SaleUpdateDTO updateDTO , Long saleId) {

        Sale sale = repository.findById(saleId).orElseThrow(() -> new SaleNotFoundException(ErrorType.NOT_FOUND.getMessage("Sale")));

        verifyQuantityExist(sale.getHarvest(), updateDTO.quantity());

        Sale toSale = mapper.fromUpdateDTO(updateDTO);
        toSale.setId(saleId);
        toSale.setUpdatedAt(LocalDateTime.now());

        return mapper.toResponseDTO(repository.save(toSale));
    }

    @Override
    public SaleResponseDTO delete(Long saleId) {

        Sale sale = repository.findById(saleId).orElseThrow(() -> new SaleNotFoundException(ErrorType.NOT_FOUND.getMessage("Sale")));
        repository.delete(sale);
        return mapper.toResponseDTO(sale);

    }

    @Override
    public SaleResponseDTO read(Long saleId) {
        Sale sale = repository.findById(saleId).orElseThrow(() -> new SaleNotFoundException(ErrorType.NOT_FOUND.getMessage("Sale")));
        return mapper.toResponseDTO(sale);
    }

    @Override
    public Page<SaleResponseDTO> readAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Sale> sales = repository.findAll(pageable);

        return sales.map(mapper::toResponseDTO);
    }

    public Sale getSaleById(Long saleId) {
        return repository.findById(saleId).orElseThrow(() -> new SaleNotFoundException(ErrorType.NOT_FOUND.getMessage("Sale")));
    }

    private double calculateSoldQty(Harvest harvest) {
        return harvest.getSales().stream()
                .mapToDouble(Sale::getQuantity)
                .sum();
    }

    private double calculateRemainingQty(Harvest harvest) {
        return harvest.getQuantityTotal() - calculateSoldQty(harvest);
    }

    private void verifyQuantityExist(Harvest harvest , double qty) {
       double remainingQty = calculateRemainingQty(harvest);
       if (remainingQty < qty) {
           throw new IllegalArgumentException("Insufficient quantity available.");
       }
    }

    private double calculateCost(double quantity , double unitPrice) {
        return quantity * unitPrice;
    }
}
