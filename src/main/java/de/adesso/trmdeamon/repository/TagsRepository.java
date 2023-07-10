package de.adesso.trmdeamon.repository;

import de.adesso.trmdeamon.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository extends JpaRepository<Tag, Long> {
}
