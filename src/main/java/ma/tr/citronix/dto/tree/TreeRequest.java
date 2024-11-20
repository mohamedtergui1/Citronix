package ma.tr.citronix.dto.tree;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record TreeRequest(
    @NotNull @Pattern(regexp = "yyyy-mm-dd") LocalDate plantation,
                Long field
) {
}
