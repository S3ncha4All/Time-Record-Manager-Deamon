package de.adesso.trmdeamon.repository;

import de.adesso.trmdeamon.model.BucketAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketAttributesRepository extends JpaRepository<BucketAttribute, Long> {
}
