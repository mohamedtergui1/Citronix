package ma.tr.citronix.dto.tree;

import ma.tr.citronix.dto.field.FieldResponse;
import java.time.LocalDate;
import java.time.Period;

public record TreeResponse(
        Long id,
        LocalDate plantation,
        FieldResponse field,
        int age
) {
    public TreeResponse(Long id, LocalDate plantation, FieldResponse field) {
        this(id, plantation, field, calculateAge(plantation));
    }

    private static int calculateAge(LocalDate plantation) {

        if (plantation == null) {
            return 0;
        }
        return Period.between(plantation, LocalDate.now()).getYears();
    }
}
