package de.adesso.trmdeamon.service;

import de.adesso.trmdeamon.dto.SettingDto;
import de.adesso.trmdeamon.mapper.Mapper;
import de.adesso.trmdeamon.model.Setting;
import de.adesso.trmdeamon.repository.SettingsRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SettingService {

    private final Mapper<Setting, SettingDto> mapper = new Mapper<Setting, SettingDto>() {
        @Override
        public SettingDto fromEntity(Setting setting) {
            return SettingDto.builder()
                    .id(setting.getId())
                    .name(setting.getName())
                    .value(setting.getValue())
                    .timeSheetId(setting.getTimeSheet().getId())
                    .build();
        }

        @Override
        public Setting fromDto(SettingDto dto) {
            return Setting.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .value(dto.getValue())
                    .build();
        }
    };

    private final SettingsRepository repository;

    public SettingDto createSetting(SettingDto dto) {
        if(dto.getId() != null) throw new RuntimeException("ID was given");
        if(StringUtils.isEmpty(dto.getName())) throw new RuntimeException("No Name given");
        if(StringUtils.isEmpty(dto.getValue())) throw new RuntimeException("No Value given");
        Setting s = mapper.fromDto(dto);
        return mapper.fromEntity(repository.save(s));
    }

    public SettingDto updateSetting(SettingDto dto) {
        if(dto.getId() == null) throw new RuntimeException("No ID was given");
        Setting s = repository.findById(dto.getId()).orElseThrow(
                () -> new RuntimeException("ID not found")
        );
        if(!StringUtils.isEmpty(dto.getName())) {
            s.setName(dto.getName());
        }
        if(!StringUtils.isEmpty(dto.getValue())) {
            s.setValue(dto.getValue());
        }
        return mapper.fromEntity(repository.save(s));
    }

    public void deleteSetting(Long id) {
        repository.deleteById(id);
    }

    public SettingDto getSetting(Long id) {
        Setting s = repository.findById(id).orElseThrow(
                () -> new RuntimeException("ID not found")
        );
        return mapper.fromEntity(s);
    }

    public List<SettingDto> getAllSettings() {
        return mapper.listFromEntity(repository.findAll());
    }
}
