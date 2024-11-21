package ma.youcode.citronix.entities.embedded;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class TreeHarvestId {

    private Long treeId;
    private Long harvestId;

}
