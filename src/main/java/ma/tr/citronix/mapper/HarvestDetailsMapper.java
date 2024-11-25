package ma.tr.citronix.mapper;

import ma.tr.citronix.dto.harvestdetails.HarvestDetailsResponse;
import ma.tr.citronix.entity.HarvestDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HarvestDetailsMapper {
    @Mapping(source = "harvest.field.farm.name", target = "farmName")
    @Mapping(source = "tree.id", target = "treeId")
    @Mapping(source = "tree.field.id", target = "fieldId")
    @Mapping(source = "harvest.id", target = "harvestId")
    HarvestDetailsResponse toHarvestDetails(HarvestDetails harvestDetails);
}
