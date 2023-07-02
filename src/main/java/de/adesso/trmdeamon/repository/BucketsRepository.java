package de.adesso.trmdeamon.repository;

import de.adesso.trmdeamon.model.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketsRepository extends JpaRepository<Bucket, Long> {
}
