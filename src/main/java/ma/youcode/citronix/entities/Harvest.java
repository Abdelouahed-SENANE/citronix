package ma.youcode.citronix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.senane.utilities.entities.BaseEntity;
import ma.youcode.citronix.enums.SeasonType;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "harvests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Harvest extends BaseEntity {

    @Column(name = "year")
    private Year year;

    @Enumerated(EnumType.STRING)
    @Column(name = "season_type")
    private SeasonType seasonType;

    @OneToMany(mappedBy = "harvest" , fetch = FetchType.EAGER)
    List<TreeHarvest> treeHarvests = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "field_id")
    private Field field;

}
