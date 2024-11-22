package ma.tr.citronix.service.field;

import lombok.RequiredArgsConstructor;
import ma.tr.citronix.dto.field.FieldRequest;
import ma.tr.citronix.entity.Farm;
import ma.tr.citronix.entity.Field;
import ma.tr.citronix.exception.NotCompleteProcess;
import ma.tr.citronix.exception.NotFoundException;
import ma.tr.citronix.mapper.FarmMapper;
import ma.tr.citronix.mapper.FieldMapper;
import ma.tr.citronix.repository.FarmRepository;
import ma.tr.citronix.repository.FieldRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Field getFieldById(Long id) {

        return fieldRepository.findById(id).orElseThrow(() -> new NotFoundException("Field not found"));

    }

    @Override
    @Transactional
    public void deleteFieldById(Long id) {

        if (fieldRepository.existsById(id)) {
            throw new NotFoundException("Field not found");
        }
        fieldRepository.deleteById(id);

    }

    @Override
    @Transactional
    public Field createField(Field field) {

        Farm farm = farmRepository.findById(field.getFarm().getId()).orElseThrow(() -> new NotCompleteProcess("Farm not found"));
        if (fieldRepository.getCountByFarmId(field.getId()) >= 10) {
            throw new NotCompleteProcess("the count of fields over 10");
        }
        if (farm.getArea() < 2 * field.getArea()) {
            throw new NotCompleteProcess("field area must not over the half farm area");
        }
        if (farm.getArea() - fieldRepository.calculateSumOfFieldsAreaByFarmId(farm.getId()).orElse(0.0) - field.getArea() < 0) {
            throw new NotCompleteProcess("area isn't enough");
        }
        field =  fieldRepository.save(field);
        field.setFarm(farm);
        return field;

    }

    @Override
    @Transactional
    public Field updateField(Long id, FieldRequest fieldRequest) {

        Field existField = fieldRepository.findById(id).orElseThrow(() -> new NotCompleteProcess("Field not found"));
        Farm farm = farmRepository.findById(fieldRequest.farmId()).orElseThrow(() -> new NotCompleteProcess("Farm not found"));

        if (farm.getArea() < 2 * fieldRequest.area()) {
            throw new NotCompleteProcess("field area must not over the half farm area");
        }
        if (farm.getArea() + existField.getArea() - fieldRepository.calculateSumOfFieldsAreaByFarmId(farm.getId()).orElse(0.0) - fieldRequest.area() < 0) {
            throw new NotCompleteProcess("area isn't enough");
        }

        Field newField = fieldMapper.toField(fieldRequest);
        newField.setId(id);
        return fieldRepository.save(newField);

    }
}
