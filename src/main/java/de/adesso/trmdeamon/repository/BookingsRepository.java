package de.adesso.trmdeamon.repository;

import de.adesso.trmdeamon.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingsRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.timeSheet = :timeSheetId")
    List<Booking> findAllBookingsForTimeSheetId(@Param("timeSheetId") Long timeSheetId);
}
