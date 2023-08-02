package de.adesso.trmdeamon.repository;

import de.adesso.trmdeamon.model.Tag;
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
public interface TagsRepository extends PagingAndSortingRepository<Tag, Long>, CrudRepository<Tag, Long> {

    @Query("SELECT t FROM Tag t WHERE t.name IN :names")
    List<Tag> findAllByName(@Param("names") List<String> names);

    @Query("SELECT t FROM Tag t WHERE t.name LIKE :name")
    Page<Tag> findAllByName(@Param("name") String name, Pageable pageable);
}
