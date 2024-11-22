package ma.youcode.citronix.repositories;

import ma.youcode.citronix.entities.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FieldRepository extends JpaRepository<Field, Long> {
    @Query("select sum(f.surface) from Field f where f.farm.id = :farmId")
    int computeFieldsSurface(@Param("farmId") Long farmId);
}
