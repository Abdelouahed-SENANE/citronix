package ma.youcode.citronix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.senane.utilities.entities.BaseEntity;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "farm" ,fetch = FetchType.EAGER)
    private List<Field> fields = new ArrayList<>();

}
