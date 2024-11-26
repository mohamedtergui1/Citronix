package ma.tr.citronix.repository;

import ma.tr.citronix.entity.Harvest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HarvestRepository extends JpaRepository<Harvest, Long> {
    @Query(value = "SELECT COUNT(*) FROM harvets h WHERE h.field_id = :fieldId AND h.season = :harvest_season AND SUBSTRING(h.date::text, 1, 4)::INTEGER = :harvest_date_year", nativeQuery = true)
    int countHarvestDetailsByFieldIdAndHarvestSeasonAndHarvestDateYear(long fieldId, String harvest_season, int harvest_date_year);

}
