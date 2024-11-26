package ma.tr.citronix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.tr.citronix.enums.Season;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "harvets")
public class Harvest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private Double quantity;

    @Enumerated(EnumType.STRING)
    private Season season;

    @OneToMany(mappedBy = "harvest")
    private List<Sale> sales;



}
