package ma.tr.citronix.service.field;

import lombok.RequiredArgsConstructor;
import ma.tr.citronix.dto.field.FieldRequest;
import ma.tr.citronix.entity.Field;
import ma.tr.citronix.exception.NotFoundException;
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
        if(fieldRepository.existsById(id)) {
            throw new NotFoundException("Field not found");
        }
        fieldRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Field addField(Field farm) {
        return null;
    }

    @Override
    @Transactional
    public Field updateField(Long id, FieldRequest farmRequest) {
        return null;
    }
}
