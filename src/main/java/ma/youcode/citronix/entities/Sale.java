package ma.youcode.citronix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.senane.utilities.entities.BaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sales")
public class Sale extends BaseEntity {

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "sold_at")
    private LocalDateTime soldAt;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "client_name")
    private String clientName;

    @ManyToOne
    @JoinColumn(name = "harvest_id")
    private Harvest harvest;

    @Transient
    private double totalPrice;

    @PrePersist
    public void prePersist() {
        this.soldAt = LocalDateTime.now();
    }


}
