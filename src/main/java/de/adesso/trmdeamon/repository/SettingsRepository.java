package de.adesso.trmdeamon.repository;

import de.adesso.trmdeamon.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepository extends JpaRepository<Setting, Long> {
}
