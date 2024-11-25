package ma.tr.citronix.dto.harvest;

import ma.tr.citronix.dto.field.FieldResponse;
import ma.tr.citronix.enums.Season;

import java.time.LocalDate;

public record HarvestResponse(
        LocalDate date,
        Season season,
        Double quantity) {
}
