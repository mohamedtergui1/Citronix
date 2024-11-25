package ma.tr.citronix.mapper;

import ma.tr.citronix.dto.harvest.HarvestRequest;
import ma.tr.citronix.dto.harvest.HarvestResponse;
import ma.tr.citronix.entity.Harvest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HarvestMapper {

    Harvest toHarvest(HarvestRequest harvest);

    HarvestResponse toHarvestResponse(Harvest harvest);
}
