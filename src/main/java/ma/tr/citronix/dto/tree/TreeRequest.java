package ma.tr.citronix.dto.tree;



import jakarta.validation.constraints.NotNull;
import ma.tr.citronix.validation.ValidMonthRange;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record TreeRequest(
       @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd")  @ValidMonthRange(min = 3 , max = 5) LocalDate plantationDate,
       @NotNull  Long fieldId
) {
}
