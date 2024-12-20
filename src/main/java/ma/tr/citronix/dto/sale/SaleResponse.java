package ma.tr.citronix.dto.sale;


import lombok.Builder;
import lombok.Getter;
import ma.tr.citronix.dto.harvest.HarvestResponse;

import java.time.LocalDate;


@Getter
public class SaleResponse {

    private Long id;
    private LocalDate date;
    private double unitPrice;
    private String client;
    private HarvestResponse harvest;
    private double quantity;
    private double income;

    public SaleResponse(Long id, LocalDate date, double unitPrice, String client, HarvestResponse harvest, double quantity) {
        this.id = id;
        this.date = date;
        this.unitPrice = unitPrice;
        this.client = client;
        this.harvest = harvest;
        this.quantity = quantity;
        this.income = unitPrice * quantity;
    }


}
