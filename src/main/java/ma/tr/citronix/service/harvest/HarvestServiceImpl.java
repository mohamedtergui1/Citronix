package ma.tr.citronix.service.harvest;

import lombok.RequiredArgsConstructor;
import ma.tr.citronix.dto.harvest.HarvestRequest;

import ma.tr.citronix.dto.harvest.HarvestResponse;
import ma.tr.citronix.entity.Field;
import ma.tr.citronix.entity.Harvest;
import ma.tr.citronix.entity.HarvestDetails;
import ma.tr.citronix.entity.Tree;

import ma.tr.citronix.exception.NotFoundException;
import ma.tr.citronix.exception.ProcessNotCompleted;
import ma.tr.citronix.mapper.HarvestMapper;
import ma.tr.citronix.repository.FieldRepository;
import ma.tr.citronix.repository.HarvestDetailsRepository;
import ma.tr.citronix.repository.HarvestRepository;
import ma.tr.citronix.repository.TreeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HarvestServiceImpl implements HarvestService {

    private final HarvestRepository harvestRepository;
    private final TreeRepository treeRepository;
    private final HarvestMapper harvestMapper;
    private final HarvestDetailsRepository harvestDetailsRepository;
    private final FieldRepository fieldRepository;

    @Override
    @Transactional(readOnly = true)
    public List<HarvestResponse> getHarvests() {
        return harvestRepository.findAll().stream().map(harvestMapper::toHarvestResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public HarvestResponse getHarvest(Long id) {
        return harvestMapper.toHarvestResponse(harvestRepository.findById(id).orElseThrow(() -> new NotFoundException("harvest not found")));
    }


    @Override
    @Transactional
    public HarvestResponse createHarvest(HarvestRequest harvestRequest) {
        Harvest harvest = harvestMapper.toHarvest(harvestRequest);
        Long fieldId = harvestRequest.getFieldId();
        Field field = fieldRepository.findById(fieldId).orElseThrow(() -> new ProcessNotCompleted("field not found"));
        if (harvestRepository.countHarvestDetailsByFieldIdAndHarvestSeasonAndHarvestDateYear(harvestRequest.getFieldId(), harvestRequest.getSeason().name(), harvestRequest.getDate().getYear()) > 0) {
            throw new ProcessNotCompleted("this field already harvested in this season and year ");
        }
        List<Tree> trees = treeRepository.findAllByFieldId(fieldId);
        if (trees.isEmpty()) {
            throw new ProcessNotCompleted("no tree is found in this field");
        }

        harvest = harvestRepository.save(harvest);
        double quantity = 0;
        List<HarvestDetails> harvestDetails = new ArrayList<>();
        for (Tree tree : trees) {
            double treeQuantity = tree.calculateProductivity();
            harvestDetails.add(HarvestDetails.builder().harvest(harvest).tree(tree).quantity(treeQuantity).build());
            quantity += treeQuantity;
        }
        harvestDetailsRepository.saveAll(harvestDetails);
        harvest.setQuantity(quantity);

        return harvestMapper.toHarvestResponse(harvestRepository.save(harvest));

    }

    @Override
    @Transactional
    public HarvestResponse updateHarvest(Long id, HarvestRequest harvestRequest) {
        Harvest existeHarvest = harvestRepository.findById(id).orElseThrow(() -> new ProcessNotCompleted("harvest not found"));
        Harvest newHarvest = harvestMapper.toHarvest(harvestRequest);
        newHarvest.setId(id);
        newHarvest.setQuantity(existeHarvest.getQuantity());
        return harvestMapper.toHarvestResponse(harvestRepository.save(newHarvest));
    }

    @Override
    @Transactional
    public void deleteHarvest(Long id) {
        if (harvestRepository.existsById(id)) {
            harvestRepository.deleteById(id);
        }
        throw new ProcessNotCompleted("this harvest not found");
    }


}



