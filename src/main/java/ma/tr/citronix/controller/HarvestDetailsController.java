package ma.tr.citronix.controller;

import lombok.RequiredArgsConstructor;
import ma.tr.citronix.dto.harvestdetails.HarvestDetailsResponse;
import ma.tr.citronix.repository.HarvestDetailsRepository;
import ma.tr.citronix.service.harvestdetails.HarvestDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/harvestDetails")
@RequiredArgsConstructor
public class HarvestDetailsController {
    private final HarvestDetailsService harvestDetailsService;

    @GetMapping("/{id}")
    private ResponseEntity<List<HarvestDetailsResponse>> getHarvestDetailsByTreeId(@PathVariable Long id) {
        return new ResponseEntity<>(harvestDetailsService.getHarvestDetailsBy(id), HttpStatus.OK);
    }
}
