package ma.tr.citronix.dto.sale;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import ma.tr.citronix.dto.harvest.HarvestRequest;
import ma.tr.citronix.entity.Harvest;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
public record SaleRequest(
        @NotNull
        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
        @NotNull
        @Positive
        double unitPrice,
        @NotBlank
        @Size(min = 6, max = 50)
        String client,
        @NotNull
        Long harvestId,
        @Positive
        double quantity
) {
}
