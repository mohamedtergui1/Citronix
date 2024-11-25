package ma.tr.citronix.dto.farm;

import jakarta.validation.constraints.*;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
public record FarmRequest (
        @NotBlank
        @Size(min = 5, max = 255)
        String name,

        @NotBlank
        @Size(min = 10, max = 255)
        String location,

        @NotNull
        @Min(2000)
        double area,

        @NotNull
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate creationDate
) {



}

