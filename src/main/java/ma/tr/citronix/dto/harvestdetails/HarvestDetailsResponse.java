package ma.tr.citronix.dto.harvestdetails;


import lombok.AllArgsConstructor;
import lombok.Getter;
import ma.tr.citronix.dto.harvest.HarvestResponse;
import ma.tr.citronix.dto.tree.TreeResponse;


@AllArgsConstructor
@Getter
public class HarvestDetailsResponse {
    private Long id;
    private Long treeId;
    private Long fieldId;
    private String farmName;
    private Long harvestId;
    private double quantity;
}