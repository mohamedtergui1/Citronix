package ma.tr.citronix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Tree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    LocalDate plantationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id")
    Field field;


    private int calculateAge() {
        if (plantationDate == null) {
            return 0;
        }
        return Period.between(plantationDate, LocalDate.now()).getYears();
    }

    public double calculateProductivity() {
        int age = calculateAge();

        if (age > 20) {
            return 0.0;
        }

        if (age < 3) {
            return 2.5;
        } else if (age <= 10) {
            return 12.0;
        } else {
            return 20.0;
        }
    }




}
