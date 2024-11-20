package ma.youcode.citronix.repositories;

import ma.youcode.citronix.entities.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<Field, Long> {
}
