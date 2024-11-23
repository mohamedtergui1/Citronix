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
@Table(name = "tree")
public class Tree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    LocalDate plantation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id")
    Field field;


    public int calculateAge() {
        if (plantation == null) {
            return 0;
        }
        return Period.between(plantation, LocalDate.now()).getYears();
    }


}
