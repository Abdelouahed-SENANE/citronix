package ma.youcode.citronix.repositories;

import ma.youcode.citronix.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long>{}
