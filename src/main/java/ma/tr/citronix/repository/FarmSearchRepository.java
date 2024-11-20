package ma.tr.citronix.repository;

import ma.tr.citronix.entity.Farm;

import java.time.LocalDate;
import java.util.List;

public interface FarmSearchRepository {
    List<Farm> searchFarm(String name, String location , LocalDate date);
}
