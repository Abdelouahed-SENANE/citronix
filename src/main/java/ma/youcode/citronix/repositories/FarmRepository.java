package ma.youcode.citronix.repositories;

import ma.youcode.citronix.entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmRepository extends JpaRepository<Farm, Long> {
}
