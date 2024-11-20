package ma.tr.citronix.dto.field;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public record FieldRequest(
   @NotNull Long farmId,
  @NotNull @Min(1) double area)  {

}
