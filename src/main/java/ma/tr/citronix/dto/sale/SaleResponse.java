package ma.tr.citronix.dto.sale;

import ma.tr.citronix.dto.harvest.HarvestRequest;
import ma.tr.citronix.dto.harvest.HarvestResponse;

import java.time.LocalDate;

public record SaleResponse(
        Long id,
        LocalDate date,
        double unitPrice,
        String client,
        Long  harvestId,
        double quantity
) {
}
