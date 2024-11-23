package ma.tr.citronix.dto.harvest;


import ma.tr.citronix.enums.Season;

import java.time.LocalDate;
import java.util.List;

public record HarvestRequest(
        @Pas
        LocalDate date,

        Double quantity,

        Season season
) {
}
