package ma.tr.citronix.service.harvest;

import ma.tr.citronix.dto.harvest.HarvestRequest;
import ma.tr.citronix.dto.harvest.HarvestResponse;
import ma.tr.citronix.entity.Harvest;

import java.util.List;

public interface HarvestService {
    List<HarvestResponse> getHarvests();

    HarvestResponse getHarvest(Long id);

    HarvestResponse createHarvest(HarvestRequest harvestRequest);

    HarvestResponse updateHarvest(Long id, HarvestRequest harvestRequest);

    void deleteHarvest(Long id);
}