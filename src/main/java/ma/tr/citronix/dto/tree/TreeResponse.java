package ma.tr.citronix.dto.tree;


import lombok.Getter;

import ma.tr.citronix.dto.field.FieldResponse;

import java.time.LocalDate;
import java.time.Period;

@Getter
public class TreeResponse {
    private Long id;
    private LocalDate plantationDate;
    private FieldResponse field;
    private int age;

    public TreeResponse(Long id, LocalDate plantationDate, FieldResponse field) {
        this.age = calculateAge(plantationDate);
        this.id = id;
        this.plantationDate = plantationDate;
        this.field = field;
    }

    private static int calculateAge(LocalDate plantationDate) {
        if (plantationDate == null) {
            return 0;
        }
        return Period.between(plantationDate, LocalDate.now()).getYears();
    }
}

