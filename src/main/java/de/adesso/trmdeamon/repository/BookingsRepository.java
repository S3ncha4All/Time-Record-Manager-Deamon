package de.adesso.trmdeamon.repository;

import de.adesso.trmdeamon.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingsRepository extends PagingAndSortingRepository<Booking, Long>, CrudRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.timeSheet = :timeSheetId ORDER BY b.id")
    List<Booking> findAllBookingsForTimeSheetId(@Param("timeSheetId") Long timeSheetId);

    @Query("SELECT b FROM Booking b WHERE b.timeSheet = :timeSheetId")
    Page<Booking> findAllBookingsForTimeSheetId(@Param("timeSheetId") Long timeSheetId, Pageable pageable);
}
