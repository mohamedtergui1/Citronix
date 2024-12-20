package ma.tr.citronix.service.harvestdetails;

import ma.tr.citronix.dto.HarvestDetailsResponseNew;
import ma.tr.citronix.dto.TreeResponseNew;
import ma.tr.citronix.dto.harvestdetails.HarvestDetailsResponse;

import java.util.List;

public interface HarvestDetailsService {
    List<TreeResponseNew> getHarvestDetailsByTreeId(Long id);

}
