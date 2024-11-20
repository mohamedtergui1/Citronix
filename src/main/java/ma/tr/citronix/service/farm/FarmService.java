package ma.tr.citronix.service.farm;

import ma.tr.citronix.dto.farm.FarmRequest;
import ma.tr.citronix.entity.Farm;

import java.util.List;

public interface FarmService {
    List<Farm> getAllFarms();

    Farm getFarmById(Long id);

    void deleteFarmById(Long id);

    Farm addFarm(Farm farm);

    Farm updateFarm(Long id , FarmRequest farmRequest);
}
