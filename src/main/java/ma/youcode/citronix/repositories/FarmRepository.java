package ma.youcode.citronix.repositories;

import ma.youcode.citronix.entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FarmRepository extends JpaRepository<Farm, Long> , JpaSpecificationExecutor<Farm> {
}
