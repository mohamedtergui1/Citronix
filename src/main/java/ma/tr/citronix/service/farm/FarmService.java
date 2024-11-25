package ma.tr.citronix.service.farm;

import ma.tr.citronix.dto.farm.FarmRequest;
import ma.tr.citronix.dto.farm.FarmResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface FarmService {
    Page<FarmResponse> getAllFarms(int page, int size);

    FarmResponse getFarmById(Long id);

    void deleteFarmById(Long id);

    FarmResponse createFarm(FarmRequest farmRequest);

    FarmResponse updateFarm(Long id , FarmRequest farmRequest);

    List<FarmResponse> search(String name, String localisation,  LocalDate date);
}
