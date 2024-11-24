package ma.tr.citronix.service.sale;

import lombok.RequiredArgsConstructor;
import ma.tr.citronix.dto.harvest.HarvestResponse;
import ma.tr.citronix.dto.sale.SaleRequest;
import ma.tr.citronix.dto.sale.SaleResponse;
import ma.tr.citronix.entity.Harvest;
import ma.tr.citronix.entity.Sale;
import ma.tr.citronix.exception.NotFoundException;
import ma.tr.citronix.exception.ProcessNotCompleted;
import ma.tr.citronix.mapper.SaleMapper;
import ma.tr.citronix.repository.HarvestRepository;
import ma.tr.citronix.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final HarvestRepository harvestRepository;
    private final SaleMapper saleMapper;

    @Override
    public List<SaleResponse> getAllSales() {
        return saleRepository.findAll().stream().map(saleMapper::toSaleResponse).toList();
    }

    @Override
    public SaleResponse getSale(Long id) {
        return saleMapper.toSaleResponse(saleRepository.findById(id).orElseThrow(() -> new NotFoundException("Sale not found")));
    }

    @Override
    public SaleResponse createSale(SaleRequest saleRequest) {
        Long harvestId = saleRequest.harvestId();
        Sale sale = saleMapper.toSale(saleRequest);
        Harvest harvest = harvestRepository.findById(harvestId).orElseThrow(() -> new ProcessNotCompleted("Harvest not found"));
        if (harvest.getQuantity() < sale.getQuantity() + saleRepository.sumSalesQuantityByHarvestId(harvestId).orElse(0.0)) {
            throw new ProcessNotCompleted("sale quantity its over the quantity rest in this harvest completed");
        }
        sale.setHarvest(harvest);
        return saleMapper.toSaleResponse(saleRepository.save(sale));
    }

    @Override
    public SaleResponse updateSale(Long id , SaleRequest saleRequest) {
        Sale oldSale = saleRepository.findById(id).orElseThrow(() -> new ProcessNotCompleted("Sale not found"));
        Long harvestId = saleRequest.harvestId();
        Sale sale = saleMapper.toSale(saleRequest);
        Harvest harvest = harvestRepository.findById(harvestId).orElseThrow(() -> new ProcessNotCompleted("Harvest not found"));
        if (harvest.getQuantity() + oldSale.getQuantity()  < sale.getQuantity() + saleRepository.sumSalesQuantityByHarvestId(harvestId).orElse(0.0)) {
            throw new ProcessNotCompleted("sale quantity its over the quantity rest in this harvest completed");
        }
        sale.setId(id);
        sale.setHarvest(harvest);
        return saleMapper.toSaleResponse(saleRepository.save(sale));
    }

    @Override
    public void deleteSale(Long id) {
        if(!saleRepository.existsById(id)) {
            throw new ProcessNotCompleted("Sale not found");
        }
        saleRepository.deleteById(id);
    }
}
