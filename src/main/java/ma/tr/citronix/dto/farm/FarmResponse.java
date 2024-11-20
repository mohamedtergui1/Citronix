package ma.tr.citronix.dto.farm;

import java.time.LocalDateTime;


public record FarmResponse(
        Long id,
        String name,
        String location,
        double area,
        LocalDateTime creationDate
) {}
