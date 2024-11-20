package ma.youcode.citronix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.senane.utilities.entities.BaseEntity;

@Entity
@Table(name = "fields")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Field extends BaseEntity {

    @Column(name = "field_surface")
    private String surface;

    @ManyToOne
    @JoinColumn(name = "farm_id")
    private Farm farm;

}