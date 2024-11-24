package ma.tr.citronix.service.farm;

import lombok.RequiredArgsConstructor;
import ma.tr.citronix.dto.farm.FarmRequest;
import ma.tr.citronix.dto.farm.FarmResponse;
import ma.tr.citronix.entity.Farm;
import ma.tr.citronix.exception.ProcessNotCompleted;
import ma.tr.citronix.exception.NotFoundException;
import ma.tr.citronix.mapper.FarmMapper;
import ma.tr.citronix.repository.FarmRepository;
import ma.tr.citronix.repository.FarmSearchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;
    private final FarmSearchRepository farmSearchRepository;
    @Override
    @Transactional(readOnly = true)
    public List<FarmResponse> getAllFarms() {
        return farmRepository.findAll().stream().map(farmMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public FarmResponse getFarmById(Long id) {
        return  farmMapper.toResponse(farmRepository.findById(id).orElseThrow(() -> new NotFoundException("Farm not found")));
    }

    @Override
    @Transactional
    public void deleteFarmById(Long id) {
        if (farmRepository.existsById(id)) {
            farmRepository.deleteById(id);
            return;
        }
        throw new ProcessNotCompleted("no Farm with this id");
    }

    @Override
    @Transactional
    public FarmResponse createFarm(FarmRequest farmRequest) {
        Farm farm = farmMapper.toFarm(farmRequest);
        return farmMapper.toResponse(farmRepository.save(farm));
    }

    @Override
    @Transactional
    public FarmResponse updateFarm(Long id, FarmRequest farmRequest) {
        Farm existingFarm = farmRepository.findById(id).orElseThrow(() -> new ProcessNotCompleted("Farm not found"));
        Farm newFarm = farmMapper.toFarm(farmRequest);
        newFarm.setId(id);
        return farmMapper.toResponse(farmRepository.save(newFarm));
    }

    @Override
    @Transactional(readOnly = true)
    public List<FarmResponse> search(String name, String localisation, LocalDate date) {

        return farmSearchRepository.searchFarm(name, localisation, date).stream().map(farmMapper::toResponse).toList();
    }
}
