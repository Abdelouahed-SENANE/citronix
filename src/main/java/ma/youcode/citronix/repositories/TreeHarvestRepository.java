package ma.youcode.citronix.repositories;

import ma.youcode.citronix.entities.Tree;
import ma.youcode.citronix.entities.TreeHarvest;
import ma.youcode.citronix.entities.embedded.TreeHarvestId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeHarvestRepository extends JpaRepository<TreeHarvest, TreeHarvestId> {
}
