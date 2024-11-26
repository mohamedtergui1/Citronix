package ma.tr.citronix.dto.field;


import lombok.Builder;
import ma.tr.citronix.dto.farm.FarmResponse;

@Builder
public record FieldResponse(long id,

                            double area,

                            String farmName) {
}