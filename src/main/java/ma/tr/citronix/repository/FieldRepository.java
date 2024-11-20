package ma.tr.citronix.repository;

import ma.tr.citronix.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {
        @Query("SELECT SUM(f.area) FROM Field f WHERE f.farm.id = :farmId")
        Optional<Double> calculateSumOfFieldsAreaByFarmId(Long farmId);
        @Query("SELECT COUNT(*) FROM Field  f where f.farm.id = :farmId")
        Long getCountByFarmId(Long farmId);

}
