package de.adesso.trmdeamon.v1.repository;

import de.adesso.trmdeamon.v1.model.TimeSheet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSheetRepository extends PagingAndSortingRepository<TimeSheet, Long>, CrudRepository<TimeSheet, Long> {
}
