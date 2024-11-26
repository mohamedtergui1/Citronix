package ma.tr.citronix.service.field;

import lombok.RequiredArgsConstructor;
import ma.tr.citronix.dto.field.FieldRequest;
import ma.tr.citronix.dto.field.FieldResponse;
import ma.tr.citronix.entity.Farm;
import ma.tr.citronix.entity.Field;
import ma.tr.citronix.exception.ProcessNotCompleted;
import ma.tr.citronix.exception.NotFoundException;
import ma.tr.citronix.mapper.FieldMapper;
import ma.tr.citronix.repository.FarmRepository;
import ma.tr.citronix.repository.FieldRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RestController
@RequiredArgsConstructor
@Transactional
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;
    private final FarmRepository farmRepository;
    private final FieldMapper fieldMapper;

    @Override
    @Transactional(readOnly = true)
    public List<FieldResponse> getAllFields() {
        return fieldRepository.findAll().stream().map(fieldMapper::toFieldResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public FieldResponse getFieldById(Long id) {
        return fieldMapper.toFieldResponse(fieldRepository.findById(id).orElseThrow(() -> new NotFoundException("Field not found")));
    }

    @Override
    @Transactional
    public void deleteFieldById(Long id) {

        if (!fieldRepository.existsById(id)) {
            throw new ProcessNotCompleted("Field not found");
        }
        fieldRepository.deleteById(id);

    }

    @Override
    @Transactional
    public FieldResponse createField(FieldRequest fieldRequest) {
        Field field = fieldMapper.toField(fieldRequest);
        Farm farm = farmRepository.findById(field.getFarm().getId()).orElseThrow(() -> new ProcessNotCompleted("Farm not found"));
        if (fieldRepository.getCountByFarmId(farm.getId()) >= 10) {
            throw new ProcessNotCompleted("the count of fields over 10");
        }
        if (farm.getArea() < 2 * field.getArea()) {
            throw new ProcessNotCompleted("field area must not over the half farm area");
        }
        if (farm.getArea() - fieldRepository.calculateSumOfFieldsAreaByFarmId(farm.getId()).orElse(0.0) - field.getArea() < 0) {
            throw new ProcessNotCompleted("area isn't enough");
        }
        field =  fieldRepository.save(field);
        field.setFarm(farm);
        return (fieldMapper.toFieldResponse(field));

    }

    @Override
    @Transactional
    public FieldResponse updateField(Long id, FieldRequest fieldRequest) {

        Field existField = fieldRepository.findById(id).orElseThrow(() -> new ProcessNotCompleted("Field not found"));
        Farm farm = farmRepository.findById(fieldRequest.farmId()).orElseThrow(() -> new ProcessNotCompleted("Farm not found"));

        if (farm.getArea() < 2 * fieldRequest.area()) {
            throw new ProcessNotCompleted("field area must not over the half farm area");
        }
        if (farm.getArea() + existField.getArea() - fieldRepository.calculateSumOfFieldsAreaByFarmId(farm.getId()).orElse(0.0) - fieldRequest.area() < 0) {
            throw new ProcessNotCompleted("area isn't enough");
        }

        Field newField = fieldMapper.toField(fieldRequest);
        newField.setId(id);
        return fieldMapper.toFieldResponse(fieldRepository.save(newField));

    }
}
