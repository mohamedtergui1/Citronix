package ma.tr.citronix.service.farm;

import ma.tr.citronix.entity.Farm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ma.tr.citronix.exception.ProcessNotCompleted;
import ma.tr.citronix.exception.NotFoundException;
import ma.tr.citronix.dto.farm.*;
import ma.tr.citronix.repository.FarmSearchRepository;
import ma.tr.citronix.repository.FarmRepository;
import ma.tr.citronix.mapper.FarmMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FarmServiceImplTest {

    @Mock
    private FarmRepository farmRepository;

    @Mock
    private FarmMapper farmMapper;

    @Mock
    private FarmSearchRepository farmSearchRepository;

    @InjectMocks
    private FarmServiceImpl farmService;

    private Farm farm;
    private FarmResponse farmResponse;
    private LocalDate testDate;

    private FarmRequest farmRequest;

    @BeforeEach
    void setUp() {
        testDate = LocalDate.now();


        farm = Farm.builder().id(1L).area(100.0).creationDate(testDate).fields(new ArrayList<>()).location("Test Location").name("Test Farm").build();


        // Setup test DTOs
        farmRequest = new FarmRequest("Test Farm", "Test Location", 100.0, testDate);
        farmResponse = new FarmResponse(1L, "Test Farm", "Test Location", 100.0, testDate);
    }





    @Test
    void getFarmById_WhenFarmExists_ShouldReturnFarmResponse() {

        Long id = 1L;
        when(farmRepository.findById(id)).thenReturn(Optional.of(farm));
        when(farmMapper.toResponse(farm)).thenReturn(farmResponse);


        FarmResponse result = farmService.getFarmById(id);


        assertNotNull(result);
        assertEquals(farmResponse.id(), result.id());
        assertEquals(farmResponse.name(), result.name());
        assertEquals(farmResponse.location(), result.location());
        assertEquals(farmResponse.area(), result.area());
        assertEquals(farmResponse.creationDate(), result.creationDate());
        verify(farmRepository,times(1)).findById(id);
    }

    @Test
    void getAllFarms_ShouldReturnListOfFarmResponses() {


        Farm farm = new Farm();

        List<Farm> farms = List.of(farm);


        Page<Farm> pagedFarms = new PageImpl<>(farms);


        when(farmRepository.findAll(any(Pageable.class))).thenReturn(pagedFarms);
        when(farmMapper.toResponse(any(Farm.class))).thenReturn(farmResponse);


        Page<FarmResponse> result = farmService.getAllFarms(1, 10);


        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(farmResponse, result.getContent().get(0));


        verify(farmRepository).findAll(any(Pageable.class));
        verify(farmMapper).toResponse(farm);
    }

    @Test
    void getFarmById_WhenFarmDoesNotExist_ShouldThrowNotFoundException() {

        Long id = 1L;
        when(farmRepository.findById(id)).thenReturn(Optional.empty());


        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> farmService.getFarmById(id));
        assertEquals("Farm not found", exception.getMessage());
        verify(farmRepository).findById(id);
    }

    @Test
    void deleteFarmById_WhenFarmExists_ShouldDeleteSuccessfully() {

        Long id = 1L;
        when(farmRepository.existsById(id)).thenReturn(true);


        farmService.deleteFarmById(id);


        verify(farmRepository).existsById(id);
        verify(farmRepository).deleteById(id);
    }

    @Test
    void deleteFarmById_WhenFarmDoesNotExist_ShouldThrowProcessNotCompleted() {

        Long id = 1L;
        when(farmRepository.existsById(id)).thenReturn(false);

        ProcessNotCompleted exception = assertThrows(ProcessNotCompleted.class,
                () -> farmService.deleteFarmById(id));
        assertEquals("no Farm with this id", exception.getMessage());
        verify(farmRepository).existsById(id);
        verify(farmRepository, never()).deleteById(any());
    }

    @Test
    void createFarm_ShouldReturnCreatedFarmResponse() {

        when(farmMapper.toFarm(farmRequest)).thenReturn(farm);
        when(farmRepository.save(any(Farm.class))).thenReturn(farm);
        when(farmMapper.toResponse(farm)).thenReturn(farmResponse);

        FarmResponse result = farmService.createFarm(farmRequest);

        assertNotNull(result);
        assertEquals(farmResponse.name(), result.name());
        assertEquals(farmResponse.location(), result.location());
        assertEquals(farmResponse.area(), result.area());
        assertEquals(farmResponse.creationDate(), result.creationDate());
        verify(farmMapper).toFarm(farmRequest);
        verify(farmRepository).save(farm);
    }

    @Test
    void updateFarm_WhenFarmExists_ShouldReturnUpdatedFarmResponse() {
        Long id = 1L;
        Farm updatedFarm = new Farm();
        updatedFarm.setId(id);
        updatedFarm.setName("Updated Farm");
        updatedFarm.setLocation("Updated Location");
        updatedFarm.setArea(200.0);
        updatedFarm.setCreationDate(testDate);

        FarmRequest updateRequest = new FarmRequest("Updated Farm", "Updated Location", 200.0, testDate);
        FarmResponse updatedResponse = new FarmResponse(id, "Updated Farm", "Updated Location", 200.0, testDate);

        when(farmRepository.findById(id)).thenReturn(Optional.of(farm));
        when(farmMapper.toFarm(updateRequest)).thenReturn(updatedFarm);
        when(farmRepository.save(any(Farm.class))).thenReturn(updatedFarm);
        when(farmMapper.toResponse(updatedFarm)).thenReturn(updatedResponse);

        FarmResponse result = farmService.updateFarm(id, updateRequest);

        assertNotNull(result);
        assertEquals(updatedResponse.name(), result.name());
        assertEquals(updatedResponse.location(), result.location());
        assertEquals(updatedResponse.area(), result.area());
        verify(farmRepository).findById(id);
        verify(farmMapper).toFarm(updateRequest);
        verify(farmRepository).save(any(Farm.class));
    }

    @Test
    void updateFarm_WhenFarmDoesNotExist_ShouldThrowProcessNotCompleted() {

        Long id = 1L;
        when(farmRepository.findById(id)).thenReturn(Optional.empty());


        ProcessNotCompleted exception = assertThrows(ProcessNotCompleted.class,
                () -> farmService.updateFarm(id, farmRequest));
        assertEquals("Farm not found", exception.getMessage());
        verify(farmRepository).findById(id);
        verify(farmRepository, never()).save(any());
    }

    @Test
    void search_ShouldReturnMatchingFarms() {

        String name = "Test Farm";
        String location = "Test Location";
        LocalDate date = testDate;
        List<Farm> farms = List.of(farm);

        when(farmSearchRepository.searchFarm(name, location, date)).thenReturn(farms);
        when(farmMapper.toResponse(any(Farm.class))).thenReturn(farmResponse);

        List<FarmResponse> results = farmService.search(name, location, date);

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(farmResponse, results.get(0));
        verify(farmSearchRepository).searchFarm(name, location, date);
        verify(farmMapper).toResponse(farm);
    }

    @Test
    void search_WhenNoMatchesFound_ShouldReturnEmptyList() {
        String name = "Nonexistent Farm";
        String location = "Nowhere";
        LocalDate date = testDate;

        when(farmSearchRepository.searchFarm(name, location, date)).thenReturn(List.of());

        List<FarmResponse> results = farmService.search(name, location, date);

        assertNotNull(results);
        assertTrue(results.isEmpty());
        verify(farmSearchRepository).searchFarm(name, location, date);
        verify(farmMapper, never()).toResponse(any());
    }
    @Test
    void test() {
        Long id = 1L;
        when(farmRepository.existsById(id)).thenReturn(false);


        ProcessNotCompleted exception = assertThrows(ProcessNotCompleted.class, () -> {
            farmService.deleteFarmById(id);
        });


        assertEquals("no Farm with this id", exception.getMessage());
        verify(farmRepository,never()).deleteById(id);
        verify(farmRepository,times(1)).existsById(id);
    }

}