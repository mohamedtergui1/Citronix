package ma.tr.citronix.dto.farm;

import jakarta.validation.constraints.*;


public record FarmRequest (
        @NotBlank
        @Size(min = 5, max = 255)
        String name,

        @NotBlank
        @Size(min = 10, max = 255)
        String location,

        @NotNull
        @Min(1)
        @Max(Long.MAX_VALUE)
        double area

) {



}

