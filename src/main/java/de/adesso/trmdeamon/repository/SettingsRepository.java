package de.adesso.trmdeamon.repository;

import de.adesso.trmdeamon.model.Setting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettingsRepository extends JpaRepository<Setting, Long> {

    @Query("SELECT s FROM Setting s WHERE s.timeSheet = :timeSheetId")
    List<Setting> findAllSettingsForTimeSheet(@Param("timeSheetId") Long timeSheetId);

    @Query("SELECT s FROM Setting s WHERE s.timeSheet = :timeSheetId")
    Page<Setting> findAllSettingsForTimeSheet(@Param("timeSheetId") Long timeSheetId, Pageable pageable);
}
