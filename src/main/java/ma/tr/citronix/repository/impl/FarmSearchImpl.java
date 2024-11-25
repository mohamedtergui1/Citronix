package ma.tr.citronix.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import ma.tr.citronix.entity.Farm;
import ma.tr.citronix.repository.FarmSearchRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
@Repository
public class FarmSearchImpl  implements FarmSearchRepository {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<Farm> searchFarm(String name, String location , LocalDate date) {


            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Farm> criteriaQuery = criteriaBuilder.createQuery(Farm.class);

            // Create the root for the query (Farm entity)
            Root<Farm> farmRoot = criteriaQuery.from(Farm.class);

            // List to store the predicates (conditions)
            List<Predicate> predicates = new ArrayList<>();

            // Add condition for 'name' if provided
            if (name != null && !name.isEmpty()) {
                Predicate namePredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(farmRoot.get("name")), "%" + name.toLowerCase() + "%"
                );
                predicates.add(namePredicate);
            }

            // Add condition for 'location' if provided
            if (location != null && !location.isEmpty()) {
                Predicate locationPredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(farmRoot.get("location")), "%" + location.toLowerCase() + "%"
                );
                predicates.add(locationPredicate);
            }

            // Add condition for 'date' if provided
            if (date != null) {
                Predicate datePredicate = criteriaBuilder.equal(
                        farmRoot.get("creationDate"), date.atStartOfDay()
                );
                predicates.add(datePredicate);
            }

            // Combine all predicates with AND
            criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));


            TypedQuery<Farm> query = entityManager.createQuery(criteriaQuery);
            return query.getResultList();

    }
}
