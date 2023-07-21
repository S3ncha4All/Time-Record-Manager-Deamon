package de.adesso.trmdeamon.repository;

import de.adesso.trmdeamon.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagsRepository extends JpaRepository<Tag, Long> {

    @Query("SELECT t FROM Tag t WHERE t.name IN :names")
    List<Tag> findAllByName(@Param("names") List<String> names);

}
