package ma.tr.citronix.dto.tree;


import lombok.Getter;

import ma.tr.citronix.dto.field.FieldResponse;

import java.time.LocalDate;
import java.time.Period;

@Getter
public class TreeResponse {
    private Long id;
    private LocalDate plantationDate;
    private String farmName;
    private Long fieldId;
    private int age;
    private double productivity;
    public TreeResponse(Long id, LocalDate plantationDate , String farmName , Long fieldId) {
        this.age = calculateAge(plantationDate);
        this.id = id;
        this.plantationDate = plantationDate;
        this.farmName = farmName;
        this.fieldId = fieldId;
        this.productivity =calculateProductivity(this.age);
    }

    private static int calculateAge(LocalDate plantationDate) {
        if (plantationDate == null) {
            return 0;
        }
        return Period.between(plantationDate, LocalDate.now()).getYears();
    }
    public double calculateProductivity(int age) {


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

