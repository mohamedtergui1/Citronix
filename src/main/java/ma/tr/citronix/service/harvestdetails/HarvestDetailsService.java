package ma.tr.citronix.service.harvestdetails;

import ma.tr.citronix.dto.harvestdetails.HarvestDetailsResponse;

import java.util.List;

public interface HarvestDetailsService {
    List<HarvestDetailsResponse> getHarvestDetailsByTreeId(Long id);

}
