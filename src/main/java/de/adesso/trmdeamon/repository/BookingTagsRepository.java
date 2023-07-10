package de.adesso.trmdeamon.repository;

import de.adesso.trmdeamon.model.Booking;
import de.adesso.trmdeamon.model.BookingTags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingTagsRepository extends JpaRepository<BookingTags, Long> {
}
