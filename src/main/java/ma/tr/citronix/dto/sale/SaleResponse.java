package ma.tr.citronix.dto.sale;


import lombok.Builder;
import ma.tr.citronix.dto.harvest.HarvestResponse;

import java.time.LocalDate;

@Builder
public record SaleResponse(
        Long id,
        LocalDate date,
        double unitPrice,
        String client,
        HarvestResponse harvest,
        double quantity
) {
}
