package ma.tr.citronix.mapper;

import ma.tr.citronix.dto.farm.FarmRequest;
import ma.tr.citronix.dto.farm.FarmResponse;
import ma.tr.citronix.entity.Farm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FarmMapper {
    Farm toFarm(FarmRequest farmRequest);
    FarmResponse toResponse(Farm farm);
}