package ma.tr.citronix.dto;


import lombok.Getter;
import ma.tr.citronix.entity.HarvestDetails;

import java.time.LocalDate;
import java.util.List;

@Getter
public class TreeResponseNew {
    private Long id;
    private LocalDate plantationDate;
    private String farmName;
    private Long fieldId;
    private List<HarvestDetails> harvestDetails;
    private List<HarvestDetailsResponseNew> harvestDetailsResponse;

    public TreeResponseNew(List<HarvestDetails> harvestDetails, String farmName, Long fieldId, Long id, LocalDate plantationDate) {
        this.harvestDetails = harvestDetails;
        this.farmName = farmName;
        this.fieldId = fieldId;
        this.id = id;
        this.plantationDate = plantationDate;
    }
}
