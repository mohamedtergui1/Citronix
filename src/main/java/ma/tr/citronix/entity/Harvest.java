package ma.tr.citronix.entity;

import jakarta.persistence.*;
import ma.tr.citronix.enums.Season;

import java.time.LocalDate;

@Entity
@Table(name = "harvests")
public class Harvest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    LocalDate date;

    Double quantity;

    Season season;

}
