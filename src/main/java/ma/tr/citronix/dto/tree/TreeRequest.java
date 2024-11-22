package ma.tr.citronix.dto.tree;


import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record TreeRequest(
        @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate plantation,
        @NotNull  Long fieldId
) {
}
