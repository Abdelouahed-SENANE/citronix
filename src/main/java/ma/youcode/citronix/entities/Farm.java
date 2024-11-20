package ma.youcode.citronix.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.senane.utilities.entities.BaseEntity;

@Entity
@Table(name = "farms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Farm extends BaseEntity {

    @Column(name = "farm_name")
    private String name;
    @Column(name = "farm_location")
    private String location;
    @Column(name = "farm_surface")
    private int surface;

}
