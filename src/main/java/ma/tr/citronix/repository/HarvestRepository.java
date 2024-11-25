package ma.tr.citronix.repository;

import ma.tr.citronix.entity.Harvest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HarvestRepository extends JpaRepository<Harvest, Long> {
    @Query(value = "SELECT COUNT(*) " +
            "FROM harvets h " +
            "JOIN harvest_details hd ON h.id = hd.harvest_id " +
            "JOIN tree t ON t.id = hd.tree_id " +
            "WHERE t.field_id = :fieldId " +
            "AND h.season = :harvestSeason " +
            "AND EXTRACT(YEAR FROM h.date) = :harvestDateYear", nativeQuery = true)
    int countHarvestDetailsByFieldIdAndHarvestSeasonAndHarvestDateYear(
            long fieldId,
            String harvestSeason,
            int harvestDateYear
    );

}
