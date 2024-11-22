package ma.youcode.citronix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.senane.utilities.entities.BaseEntity;
import ma.youcode.citronix.enums.SeasonType;

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

    @Column(name = "quantity_total")
    private double quantityTotal;

    @OneToMany(mappedBy = "harvest" , fetch = FetchType.EAGER)
    private List<HarvestHistory> harvestHistories = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "field_id")
    private Field field;

    @OneToMany(mappedBy = "harvest" , fetch = FetchType.EAGER)
    private List<Sale> sales = new ArrayList<>();

}
