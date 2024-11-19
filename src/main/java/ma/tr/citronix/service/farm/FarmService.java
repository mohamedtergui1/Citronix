package ma.tr.citronix.service.farm;

import ma.tr.citronix.entity.Farm;

import java.util.List;

public interface FarmService {
    List<Farm> getAllFarms();

    Farm getFarmById(int id);

    void deleteFarmById(int id);

    Farm addFarm(Farm farm);

    Farm updateFarm(Farm farm);
}
