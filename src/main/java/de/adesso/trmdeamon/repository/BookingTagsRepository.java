package de.adesso.trmdeamon.repository;

import de.adesso.trmdeamon.model.BookingTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookingTagsRepository extends JpaRepository<BookingTags, Long> {

    @Transactional
    @Query("DELETE FROM BookingTags bt WHERE bt.booking.id = :bookingId AND bt.tag.id IN :tagIds")
    void deleteByBookingIdAndTagId(@Param("bookingId") Long bookingId, @Param("tagIds") List<Long> tagIds);

    @Transactional
    @Query("DELETE FROM BookingTags bt WHERE bt.booking.id = :bookingId AND bt.tag.name IN :tagNames")
    void deleteByBookingIdAndTagName(@Param("bookingId") Long bookingId, @Param("tagNames") List<String> tagNames);
}
