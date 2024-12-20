package ma.tr.citronix.repository;

import ma.tr.citronix.entity.Tree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


public interface TreeRepository extends JpaRepository<Tree, Long> {
        @Query("SELECT COUNT(t) FROM Tree t WHERE t.field.id = :fieldId")
        Long getCountByFieldId(Long fieldId);
        List<Tree> findAllByFieldId(Long fieldId );
        //@Query(value ="select t.* from  tree t join fields f on  f.id = t.field_id where f.farm_id = :Idfarm " , nativeQuery = true)
        List<Tree> findByFieldFarmId(Long IdFarm);
}
