package ma.tr.citronix.mapper;

import ma.tr.citronix.dto.sale.SaleRequest;
import ma.tr.citronix.dto.sale.SaleResponse;
import ma.tr.citronix.entity.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    @Mapping(source = "harvestId", target = "harvest.id")
    Sale toSale(SaleRequest saleRequest);
    @Mapping(source = "harvest.id" , target = "harvestId")
    SaleResponse toSaleResponse(Sale sale);
}
