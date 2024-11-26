package ma.tr.citronix.service.field;

import ma.tr.citronix.dto.field.FieldRequest;
import ma.tr.citronix.dto.field.FieldResponse;
import ma.tr.citronix.entity.Field;

import java.util.List;

public interface FieldService {
    List<FieldResponse> getAllFields();

    FieldResponse getFieldById(Long id);

    void deleteFieldById(Long id);

    FieldResponse createField(FieldRequest fieldRequest);

    FieldResponse updateField(Long id , FieldRequest farmRequest);
}
