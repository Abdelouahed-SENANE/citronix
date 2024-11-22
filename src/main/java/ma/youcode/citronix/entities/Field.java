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
@Table(name = "fields")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Field extends BaseEntity {

    @Column(name = "field_surface")
    private Integer surface;

    @ManyToOne
    @JoinColumn(name = "farm_id")
    private Farm farm;

    @OneToMany(mappedBy = "field" , fetch = FetchType.EAGER)
    private List<Tree> trees = new ArrayList<>();

    @OneToMany(mappedBy = "field" , fetch = FetchType.EAGER)
    private List<Harvest> harvests = new ArrayList<>();

}
