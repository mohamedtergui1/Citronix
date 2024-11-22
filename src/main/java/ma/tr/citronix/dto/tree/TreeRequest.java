package ma.tr.citronix.dto.tree;


import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotNull;
import ma.tr.citronix.validation.mounth.ValidMonthRange;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record TreeRequest(
       @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd")  @ValidMonthRange(min = 3 , max = 5) LocalDate plantation,
        @NotNull  Long fieldId
) {
}
