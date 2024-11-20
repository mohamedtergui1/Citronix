package ma.tr.citronix.service.farm;

import lombok.RequiredArgsConstructor;
import ma.tr.citronix.dto.farm.FarmRequest;
import ma.tr.citronix.entity.Farm;
import ma.tr.citronix.exception.NotCompleteProcess;
import ma.tr.citronix.exception.NotFoundException;
import ma.tr.citronix.mapper.FarmMapper;
import ma.tr.citronix.repository.FarmRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Farm getFarmById(Long id) {
        return farmRepository.findById(id).orElseThrow(() -> new NotFoundException("Farm not found"));
    }

    @Override
    @Transactional
    public void deleteFarmById(Long id) {
        if (farmRepository.existsById(id)) {
            farmRepository.deleteById(id);
            return;
        }
        throw new NotCompleteProcess("no Farm with this id");
    }

    @Override
    @Transactional
    public Farm addFarm(Farm farm) {
        return farmRepository.save(farm);
    }

    @Override
    @Transactional
    public Farm updateFarm(Long id, FarmRequest farmRequest) {
        Farm existingFarm = farmRepository.findById(id).orElseThrow(() -> new NotCompleteProcess("Farm not found"));
        Farm newFarm = farmMapper.toFarm(farmRequest);
        newFarm.setId(id);
        newFarm.setCreationDate(existingFarm.getCreationDate());
        return farmRepository.save(newFarm);
    }
}
