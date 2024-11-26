package ma.tr.citronix.service.farm;

import ma.tr.citronix.dto.field.FieldRequest;
import ma.tr.citronix.dto.field.FieldResponse;
import ma.tr.citronix.entity.Farm;
import ma.tr.citronix.entity.Field;
import ma.tr.citronix.exception.ProcessNotCompleted;
import ma.tr.citronix.mapper.FieldMapper;
import ma.tr.citronix.repository.FarmRepository;
import ma.tr.citronix.repository.FieldRepository;
import ma.tr.citronix.service.field.FieldServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FieldServiceImplTest {
    @InjectMocks
    private FieldServiceImpl fieldServiceImpl;
    @Mock
    private FieldRepository fieldRepository;
    @Mock
    private FarmRepository farmRepository;
    @Mock
    private FieldMapper fieldMapper;

    private Field field;

    private FieldRequest fieldRequest;
    private FieldResponse fieldResponse;


    @BeforeEach
    public void setup() {
        Farm farm = Farm.builder().id(1L).name("test").area(10000.0).build();
        field = Field.builder().id(1L).area(100.0).trees(new ArrayList<>()).farm(farm).build();
        fieldResponse = FieldResponse.builder().area(1000.0).farmName("test").id(1L).build();
        fieldRequest = FieldRequest.builder().area(1000.0).farmId(1L).build();
    }

    @Test
    public void testDeleteThrowException() {
        when(fieldRepository.existsById(1L)).thenReturn(false);
        System.out.println("testDeleteThrowException");

        ProcessNotCompleted ex = assertThrows(ProcessNotCompleted.class, () -> fieldServiceImpl.deleteFieldById(1L));

        assert (ex.getMessage().equals("Field not found"));
        System.out.println("testDeleteThrowException");
        verify(fieldRepository, never()).deleteById(1L);
        System.out.println("testDeleteThrowException");

        verify(fieldRepository, times(1)).existsById(1L);

    }
    @Test
    public void testCrete() {
        Farm farm = Farm.builder().id(1L).name("test").area(10000.0).build();
        when(fieldMapper.toField(fieldRequest)).thenReturn(field);
        when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));
        when(fieldRepository.getCountByFarmId(farm.getId())).thenReturn(7L);
        when(fieldRepository.calculateSumOfFieldsAreaByFarmId(1L)).thenReturn(Optional.empty());
        when(fieldMapper.toFieldResponse(field)).thenReturn(fieldResponse);
        when(fieldRepository.save(field)).thenReturn(field);

        FieldResponse fieldResponse1 = fieldServiceImpl.createField(fieldRequest);
        assert(fieldResponse1.farmName().equals(farm.getName()));
        assert(fieldResponse1.id() == field.getId());

    }
}
