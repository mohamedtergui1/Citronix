package ma.tr.citronix.dto.field;


import ma.tr.citronix.dto.farm.FarmResponse;


public record FieldResponse(long id,

                            double area,

                            String farmName) {
}