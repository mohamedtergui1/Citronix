package ma.tr.citronix.service.field;

import ma.tr.citronix.dto.field.FieldRequest;
import ma.tr.citronix.entity.Field;

import java.util.List;

public interface FieldService {
    List<Field> getAllFields();

    Field getFieldById(Long id);

    void deleteFieldById(Long id);

    Field addField(Field farm);

    Field updateField(Long id , FieldRequest farmRequest);
}
