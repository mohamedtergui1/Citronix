package ma.tr.citronix.mapper;

import ma.tr.citronix.dto.harvestdetails.HarvestDetailsResponse;
import ma.tr.citronix.entity.HarvestDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HarvestDetailsMapper {
    @Mapping(source = "harvest.field", target = "harvest.field", ignore = true)
    HarvestDetailsResponse toHarvestDetails(HarvestDetails harvestDetails);
}
