package de.adesso.trmdeamon.repository;

import de.adesso.trmdeamon.model.BookingAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingAttributesRepository extends JpaRepository<BookingAttribute, Long> {
}
