package ma.tr.citronix.service.harvestdetails;

import lombok.RequiredArgsConstructor;
import ma.tr.citronix.dto.harvestdetails.HarvestDetailsResponse;
import ma.tr.citronix.exception.NotFoundException;
import ma.tr.citronix.mapper.HarvestDetailsMapper;
import ma.tr.citronix.repository.HarvestDetailsRepository;
import ma.tr.citronix.repository.TreeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HarvestDetailsServiceImpl implements HarvestDetailsService {
    private final HarvestDetailsRepository harvestDetailsRepository;
    private final TreeRepository treeRepository;
    private final HarvestDetailsMapper harvestDetailsMapper;
    @Override
    public List<HarvestDetailsResponse> getHarvestDetailsBy(Long id) {
        if(!treeRepository.existsById(id))
        {
            throw  new NotFoundException("three not found");
        }
        return harvestDetailsRepository.findByHarvestId(id).stream().map(harvestDetailsMapper::toHarvestDetails).toList();
    }
}
