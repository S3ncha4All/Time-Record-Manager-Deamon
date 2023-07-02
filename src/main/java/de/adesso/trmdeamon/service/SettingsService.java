package de.adesso.trmdeamon.service;

import de.adesso.trmdeamon.dto.SettingsDto;
import de.adesso.trmdeamon.mapper.Mapper;
import de.adesso.trmdeamon.model.Setting;
import de.adesso.trmdeamon.repository.SettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SettingsService {

    private final Mapper<Setting, SettingsDto> mapper = new Mapper<Setting, SettingsDto>() {
        @Override
        public SettingsDto fromEntity(Setting setting) {
            return SettingsDto.builder()
                    .id(setting.getId())
                    .name(setting.getName())
                    .value(setting.getValue())
                    .timeSheetId(setting.getTimeSheet().getId())
                    .build();
        }

        @Override
        public Setting fromDto(SettingsDto dto) {
            return Setting.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .value(dto.getValue())
                    .build();
        }
    };

    private final SettingsRepository repository;

    public SettingsDto createSetting(SettingsDto dto) {
        if(dto.getId() != null) throw new RuntimeException("ID was given");
        Setting s = mapper.fromDto(dto);
        return mapper.fromEntity(repository.save(s));
    }

    public SettingsDto updateSetting(SettingsDto dto) {
        if(dto.getId() == null) throw new RuntimeException("No ID was given");
        Setting s = repository.findById(dto.getId()).orElseThrow(
                () -> new RuntimeException("ID not found")
        );
        if(dto.getName() != null) {
            s.setName(dto.getName());
        }
        if(dto.getValue() != null) {
            s.setValue(dto.getValue());
        }
        return mapper.fromEntity(repository.save(s));
    }

    public void deleteSetting(Long id) {
        repository.deleteById(id);
    }

    public SettingsDto getSetting(Long id) {
        Setting s = repository.findById(id).orElseThrow(
                () -> new RuntimeException("ID not found")
        );
        return mapper.fromEntity(s);
    }

    public List<SettingsDto> getAllSettings() {
        return mapper.listFromEntity(repository.findAll());
    }
}
