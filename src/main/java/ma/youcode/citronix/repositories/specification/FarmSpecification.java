package ma.youcode.citronix.repositories.specification;

import jakarta.persistence.criteria.Predicate;
import ma.youcode.citronix.dto.request.farm.FilterFarmDTO;
import ma.youcode.citronix.entities.Farm;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class FarmSpecification {

    public static Specification<Farm> filterFarm(FilterFarmDTO filter) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.name() != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + filter.name().toLowerCase() + "%"));
            }

            if (filter.location() != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("location")), "%" + filter.location().toLowerCase() + "%"));
            }

            if (filter.surface() != null) {
                predicates.add(criteriaBuilder.equal(root.get("surface"), filter.surface()));
            }
            if (filter.creationDate() != null) {
                predicates.add(criteriaBuilder.equal(root.get("creationDate"), filter.creationDate()));
            }

           return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
