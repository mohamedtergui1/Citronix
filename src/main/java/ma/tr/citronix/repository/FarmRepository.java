package ma.tr.citronix.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import ma.tr.citronix.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {
    List<Farm> findByLocationContainingIgnoreCaseOrNameContainingIgnoreCase(String location, String name);
    List<Farm> findAllByLocationContainingIgnoreCaseOrNameContainingIgnoreCaseAndCreationDate(String location, String name, LocalDateTime creationDate);
}
