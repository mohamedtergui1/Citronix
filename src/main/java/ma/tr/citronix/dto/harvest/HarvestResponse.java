package ma.tr.citronix.dto.harvest;

import lombok.Builder;
import ma.tr.citronix.dto.field.FieldResponse;
import ma.tr.citronix.enums.Season;

import java.time.LocalDate;

@Builder
public record HarvestResponse(
        Long id,
        LocalDate date,
        Season season,
        Double quantity) {
}
