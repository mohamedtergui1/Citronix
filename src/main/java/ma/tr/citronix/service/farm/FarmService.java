package ma.tr.citronix.service.farm;

import ma.tr.citronix.dto.farm.FarmRequest;
import ma.tr.citronix.entity.Farm;

import java.time.LocalDate;
import java.util.List;

public interface FarmService {
    List<Farm> getAllFarms();

    Farm getFarmById(Long id);

    void deleteFarmById(Long id);

    Farm createFarm(Farm farm);

    Farm updateFarm(Long id , FarmRequest farmRequest);

    List<Farm> search(String name, String localisation,  LocalDate date);
}
