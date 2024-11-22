package ma.tr.citronix.repository;

import ma.tr.citronix.entity.Harvest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HarvestRepository extends JpaRepository<Harvest, Long> {

}
