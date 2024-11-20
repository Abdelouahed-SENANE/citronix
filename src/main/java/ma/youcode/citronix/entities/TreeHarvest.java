package ma.youcode.citronix.entities;

import jakarta.persistence.*;
import ma.senane.utilities.entities.BaseEntity;

@Entity
@Table(name = "tree_harvests")
public class TreeHarvest extends BaseEntity {

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "harvest_id")
    private Harvest harvest;

    @ManyToOne
    @JoinColumn(name = "tree_id")
    private Tree tree;




}
