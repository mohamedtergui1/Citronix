package ma.tr.citronix.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.tr.citronix.dto.sale.SaleRequest;
import ma.tr.citronix.dto.sale.SaleResponse;
import ma.tr.citronix.service.sale.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {
    private final SaleService saleService;

    @GetMapping
    public ResponseEntity<List<SaleResponse>> sales() {
        return new ResponseEntity<>(saleService.getAllSales(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponse> saleById(@PathVariable Long id) {
        return new ResponseEntity<>(saleService.getSale(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SaleResponse> save(@RequestBody @Valid SaleRequest saleRequest) {
        return new ResponseEntity<>(saleService.createSale(saleRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleResponse> update(@PathVariable Long id, @RequestBody @Valid SaleRequest saleRequest) {
        return new ResponseEntity<>(saleService.updateSale(id, saleRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        saleService.deleteSale(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
