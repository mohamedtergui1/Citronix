package ma.tr.citronix.repository;

import ma.tr.citronix.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT SUM(s.quantity) from Sale s where s.harvest.id= :harvestId")
    Optional<Double> sumSalesQuantityByHarvestId(Long harvestId);
}
