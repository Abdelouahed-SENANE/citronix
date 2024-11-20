package ma.youcode.citronix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.senane.utilities.entities.BaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Entity
@Table(name = "trees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tree extends BaseEntity {

    @Column(name = "planting_date")
    private LocalDate plantingDate;

    @ManyToOne
    @JoinColumn(name = "field_id")
    private Field field;

    public boolean isProductive() {
        int age =  Period.between(this.plantingDate, LocalDate.now()).getYears();
        return age <= 20;
    }
}