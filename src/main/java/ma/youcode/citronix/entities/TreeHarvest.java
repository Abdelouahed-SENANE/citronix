package ma.youcode.citronix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.senane.utilities.entities.BaseEntity;
import ma.youcode.citronix.entities.embedded.TreeHarvestId;

import java.time.LocalDateTime;

@Entity
@Table(name = "tree_harvests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TreeHarvest  {

    @EmbeddedId
    @Column(name = "tree_harvest_id")
    private TreeHarvestId treeHarvestId;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "created_at")
    LocalDateTime createdAt;
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @ManyToOne
    @MapsId("harvestId")
    @JoinColumn(name = "harvest_id")
    private Harvest harvest;

    @ManyToOne
    @MapsId("treeId")
    @JoinColumn(name = "tree_id")
    private Tree tree;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }



}
