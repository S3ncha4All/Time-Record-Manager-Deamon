package de.adesso.trmdeamon.repository;

import de.adesso.trmdeamon.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributesRepository extends JpaRepository<Attribute, Long> {
}
