package de.adesso.trmdeamon.repository;

import de.adesso.trmdeamon.model.TimeSheet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSheetRepository extends PagingAndSortingRepository<TimeSheet, Long>, CrudRepository<TimeSheet, Long> {

    @Query(value = "SELECT t FROM TimeSheet t WHERE t.name LIKE :timeSheetName ORDER BY t.id")
    Page<TimeSheet> findAllTimeSheetsLikeName(@Param("timeSheetName") String name, Pageable pageable);
}
