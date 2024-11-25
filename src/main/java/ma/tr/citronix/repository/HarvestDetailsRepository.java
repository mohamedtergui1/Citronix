package ma.tr.citronix.repository;

import ma.tr.citronix.entity.HarvestDetails;
import ma.tr.citronix.enums.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface HarvestDetailsRepository extends JpaRepository<HarvestDetails, Long> {
    List<HarvestDetails> findByTreeId(Long harvestId);
}
