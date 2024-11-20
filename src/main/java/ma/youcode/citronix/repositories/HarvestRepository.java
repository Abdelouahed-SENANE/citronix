package ma.youcode.citronix.repositories;

import ma.youcode.citronix.entities.Harvest;
import ma.youcode.citronix.entities.Tree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HarvestRepository extends JpaRepository<Harvest, Long> {
}
