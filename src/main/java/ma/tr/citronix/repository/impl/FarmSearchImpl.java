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
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FarmSearchImpl implements FarmSearchRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Farm> searchFarm(String name, String location, LocalDate date) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Farm> criteriaQuery = criteriaBuilder.createQuery(Farm.class);

        // Create the root for the query (Farm entity)
        Root<Farm> farmRoot = criteriaQuery.from(Farm.class);

        // Initialize a predicate as null. This will hold the combined conditions.
        Predicate finalPredicate = null;

        // Add condition for 'name' if provided
        if (name != null && !name.isEmpty()) {
            Predicate namePredicate = criteriaBuilder.like(
                    criteriaBuilder.lower(farmRoot.get("name")), "%" + name.toLowerCase() + "%"
            );
            if (finalPredicate == null) {
                finalPredicate = namePredicate;  // If no predicate yet, start with this one
            } else {
                finalPredicate = criteriaBuilder.and(finalPredicate, namePredicate); // Combine with AND
            }
        }

        // Add condition for 'location' if provided
        if (location != null && !location.isEmpty()) {
            Predicate locationPredicate = criteriaBuilder.like(
                    criteriaBuilder.lower(farmRoot.get("location")), "%" + location.toLowerCase() + "%"
            );
            if (finalPredicate == null) {
                finalPredicate = locationPredicate;  // If no predicate yet, start with this one
            } else {
                finalPredicate = criteriaBuilder.and(finalPredicate, locationPredicate); // Combine with AND
            }
        }

        // Add condition for 'date' if provided
        if (date != null) {
            Predicate datePredicate = criteriaBuilder.equal(
                    farmRoot.get("creationDate"), date.atStartOfDay()
            );
            if (finalPredicate == null) {
                finalPredicate = datePredicate;  // If no predicate yet, start with this one
            } else {
                finalPredicate = criteriaBuilder.and(finalPredicate, datePredicate); // Combine with AND
            }
        }

        // Apply the final predicate to the query
        if (finalPredicate != null) {
            criteriaQuery.where(finalPredicate);
        }

        TypedQuery<Farm> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
