package ma.tr.citronix.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.tr.citronix.dto.field.FieldRequest;
import ma.tr.citronix.dto.field.FieldResponse;
import ma.tr.citronix.mapper.FieldMapper;
import ma.tr.citronix.service.field.FieldService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/fields")
public class FieldController {
    private final FieldService fieldService;
    private final FieldMapper fieldMapper;

    @GetMapping
    public ResponseEntity<List<FieldResponse>> getAllFields() {
        return new ResponseEntity<>(fieldService.getAllFields(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FieldResponse> getFieldById(@PathVariable Long id) {
        return new ResponseEntity<>(fieldService.getFieldById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FieldResponse> createField(@RequestBody @Valid FieldRequest fieldRequest) {
        return new ResponseEntity<>(fieldService.createField(fieldRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FieldResponse> updateField(@PathVariable Long id, @RequestBody @Valid FieldRequest fieldRequest) {
        return new ResponseEntity<>(fieldService.updateField(id, fieldRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteField(@PathVariable Long id) {
        fieldService.deleteFieldById(id);
        return new ResponseEntity<>("field deleted", HttpStatus.OK);
    }
}
