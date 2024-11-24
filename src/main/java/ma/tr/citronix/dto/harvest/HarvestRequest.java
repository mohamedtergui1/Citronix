package ma.tr.citronix.dto.harvest;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import ma.tr.citronix.enums.Season;
import ma.tr.citronix.validation.EnumValidate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Getter
public class HarvestRequest {
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @NotBlank
    @EnumValidate(enumClass = Season.class)
    String seasonStr;
    @NotNull
    Long fieldId;
    private Season season;

    public HarvestRequest(LocalDate date, Long fieldId, String seasonStr) {
        this.date = date;
        this.fieldId = fieldId;
        this.season = Season.valueOf(seasonStr);
        this.seasonStr = seasonStr;
    }
}
