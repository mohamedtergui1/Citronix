package ma.tr.citronix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private double unitPrice;
    private String client;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "harvest_id")
    private Harvest harvest;

    private double quantity;

}
