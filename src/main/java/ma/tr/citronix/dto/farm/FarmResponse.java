package ma.tr.citronix.dto.farm;

import java.time.LocalDate;



public record FarmResponse(
        Long id,
        String name,
        String location,
        double area,
        LocalDate creationDate
) {}
