package ma.tr.citronix.repository;

import ma.tr.citronix.entity.HarvestDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HarvestDetailsRepository extends JpaRepository<HarvestDetails, Integer> {
}
