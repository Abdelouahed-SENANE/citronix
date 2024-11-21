package ma.youcode.citronix.repositories;

import ma.youcode.citronix.entities.Harvest;
import ma.youcode.citronix.entities.Tree;
import ma.youcode.citronix.enums.SeasonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Year;

public interface HarvestRepository extends JpaRepository<Harvest, Long> {

    @Query("select count(*) > 0 from Harvest h where h.seasonType = :type and h.year = :year and h.field.id = :fieldId")
    boolean isFieldHarvested(@Param("type") SeasonType seasonType,@Param("year") Year year,@Param("fieldId") Long fieldId);

}
