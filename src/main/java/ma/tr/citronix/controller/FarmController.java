package ma.tr.citronix.controller;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import ma.tr.citronix.dto.farm.FarmRequest;
import ma.tr.citronix.dto.farm.FarmResponse;
import ma.tr.citronix.mapper.FarmMapper;
import ma.tr.citronix.service.farm.FarmService;
import org.springframework.data.domain.Page;
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

    @GetMapping
    public ResponseEntity<Page<FarmResponse>> getAllFarms(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<FarmResponse> farms = farmService.getAllFarms(page, size);
        return ResponseEntity.ok(farms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmResponse> getFarmById(@PathVariable Long id) {
        return ResponseEntity.ok(
                farmService.getFarmById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<FarmResponse> updateFarm(
            @PathVariable Long id,
            @RequestBody @Valid FarmRequest farmRequest
    ) {
        return ResponseEntity.ok(
                farmService.updateFarm(id, farmRequest)
        );
    }

    @PostMapping
    public ResponseEntity<FarmResponse> createFarm(
            @RequestBody @Valid FarmRequest farmRequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        farmService.createFarm(farmRequest)
                );
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
            @RequestParam(defaultValue = "") String localisation
    ) {
        return new ResponseEntity<>(farmService.search(name, localisation, date), HttpStatus.OK);
    }

}