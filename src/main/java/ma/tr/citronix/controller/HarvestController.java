package ma.tr.citronix.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.tr.citronix.dto.harvest.HarvestRequest;
import ma.tr.citronix.dto.harvest.HarvestResponse;
import ma.tr.citronix.entity.Harvest;
import ma.tr.citronix.service.harvest.HarvestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/harvests")
public class HarvestController {
    private final HarvestService harvestService;

    @GetMapping
    public ResponseEntity<List<HarvestResponse>> getAllHarvests() {
        return new ResponseEntity<>(harvestService.getHarvests(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HarvestResponse> getHarvestById(@PathVariable long id) {
        return new ResponseEntity<>(harvestService
                .getHarvest(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HarvestResponse> addHarvest(@RequestBody @Valid HarvestRequest harvestRequest) {
        return new ResponseEntity<>(harvestService.createHarvest(harvestRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HarvestResponse> updateHarvest(@PathVariable Long id, @RequestBody @Valid HarvestRequest harvestRequest) {
        return new ResponseEntity<>(harvestService.updateHarvest(id,harvestRequest),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHarvest(@PathVariable Long id) {
        harvestService.deleteHarvest(id);
        return new ResponseEntity<>( "harvest deleted" ,HttpStatus.OK);
    }

}
