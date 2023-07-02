package de.adesso.trmdeamon.repository;

import de.adesso.trmdeamon.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingsRepository extends JpaRepository<Booking, Long> {
}
