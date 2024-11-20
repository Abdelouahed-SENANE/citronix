package ma.youcode.citronix.repositories;

import ma.youcode.citronix.entities.Tree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeRepository extends JpaRepository<Tree, Long> {
}
