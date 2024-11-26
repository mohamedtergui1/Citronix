package ma.tr.citronix.mapper;

import ma.tr.citronix.dto.harvestdetails.HarvestDetailsResponse;
import ma.tr.citronix.entity.HarvestDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HarvestDetailsMapper {
    @Mapping(source = "tree.field.id" , target = "tree.fieldId")
    @Mapping(source = "tree.field.farm.name" , target = "tree.farmName")
    @Mapping(source = "harvest.quantity" , target = "quantityTotalOfHarvests")
    HarvestDetailsResponse toHarvestDetails(HarvestDetails harvestDetails);
}
