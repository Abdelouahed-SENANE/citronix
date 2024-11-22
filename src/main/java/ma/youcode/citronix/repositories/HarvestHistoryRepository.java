package ma.youcode.citronix.repositories;

import ma.youcode.citronix.entities.HarvestHistory;
import ma.youcode.citronix.entities.embedded.TreeHarvestId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HarvestHistoryRepository extends JpaRepository<HarvestHistory, TreeHarvestId> {


}
