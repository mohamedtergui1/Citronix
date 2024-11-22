package ma.tr.citronix.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.tr.citronix.dto.farm.FarmRequest;
import ma.tr.citronix.dto.farm.FarmResponse;
import ma.tr.citronix.mapper.FarmMapper;
import ma.tr.citronix.service.farm.FarmService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/farms")
@RestController
public class FarmController {
    private final FarmService farmService;
    private final FarmMapper farmMapper;

    @GetMapping
    public ResponseEntity<List<FarmResponse>> getAllFarms() {
        List<FarmResponse> farms = farmService.getAllFarms()
                .stream()
                .map(farmMapper::toResponse)
                .toList();
        return ResponseEntity.ok(farms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmResponse> getFarmById(@PathVariable Long id) {
        return ResponseEntity.ok(
                farmMapper.toResponse(farmService.getFarmById(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<FarmResponse> updateFarm(
            @PathVariable Long id,
            @RequestBody @Valid FarmRequest farmRequest
    ) {
        return ResponseEntity.ok(
                farmMapper.toResponse(farmService.updateFarm(id, farmRequest))
        );
    }

    @PostMapping
    public ResponseEntity<FarmResponse> createFarm(
            @RequestBody @Valid FarmRequest farmRequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(farmMapper.toResponse(
                        farmService.createFarm(farmMapper.toFarm(farmRequest))
                ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarm(@PathVariable Long id) {
        farmService.deleteFarmById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<FarmResponse>> searchFarms(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam(defaultValue = "") String localisation,
            @RequestParam(defaultValue = "0") double area
    ){
            return new ResponseEntity<>(farmService.search(name, localisation, date).stream().map(farmMapper::toResponse).toList(), HttpStatus.OK) ;
    }

}