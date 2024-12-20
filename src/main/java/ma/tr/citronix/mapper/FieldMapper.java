package ma.tr.citronix.mapper;

import ma.tr.citronix.dto.farm.FarmRequest;
import ma.tr.citronix.dto.field.FieldRequest;
import ma.tr.citronix.dto.field.FieldResponse;
import ma.tr.citronix.entity.Field;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FieldMapper {
    @Mapping(source = "farmId" , target = "farm.id")
    Field toField(FieldRequest fieldRequest);
    @Mapping(source = "farm.name" , target = "farmName")
    FieldResponse toFieldResponse(Field field);
}
