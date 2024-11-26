package ma.tr.citronix.service.sale;

import ma.tr.citronix.dto.sale.SaleRequest;
import ma.tr.citronix.dto.sale.SaleResponse;

import java.util.List;

public interface SaleService {
    List<SaleResponse> getAllSales();

    SaleResponse getSale(Long id);

    SaleResponse createSale(SaleRequest saleRequest);

    SaleResponse updateSale(Long id ,SaleRequest saleRequest);

    void deleteSale(Long id);
}
