package de.adesso.trmdeamon.service;

import de.adesso.trmdeamon.dto.settings.ConstructSettingDto;
import de.adesso.trmdeamon.dto.settings.SettingDto;
import de.adesso.trmdeamon.dto.timesheet.TimeSheetDto;
import de.adesso.trmdeamon.mapper.Mapper;
import de.adesso.trmdeamon.model.Setting;
import de.adesso.trmdeamon.model.TimeSheet;
import de.adesso.trmdeamon.repository.SettingsRepository;
import de.adesso.trmdeamon.repository.TimeSheetRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SettingService {

    public static final Mapper<Setting, ConstructSettingDto> constructionMapper = new Mapper<>() {
        @Override
        public Setting fromDto(ConstructSettingDto dto) {
            return Setting.builder()
                    .name(dto.getName())
                    .value(dto.getValue())
                    .build();
        }
    };

    public static final Mapper<Setting, SettingDto> settingSettingMapper = new Mapper<>() {
        @Override
        public SettingDto fromEntity(Setting setting) {
            return SettingDto.builder()
                    .id(setting.getId())
                    .name(setting.getName())
                    .value(setting.getValue())
                    .build();
        }
    };

    private final SettingsRepository repository;
    private final TimeSheetRepository timeSheetRepository;

    public TimeSheetDto createSetting(Long timeSheetId, ConstructSettingDto dto) {
        Setting s = constructionMapper.fromDto(dto);
        TimeSheet ts = timeSheetRepository.findById(timeSheetId).orElseThrow(
                () -> new RuntimeException("Time Sheet not found")
        );
        s.setTimeSheet(ts);
        repository.save(s);
        return getTimeSheet(timeSheetId);
    }

    public TimeSheetDto updateSetting(Long timeSheetsId, Long settingId, ConstructSettingDto dto) {
        Setting s = repository.findById(settingId).orElseThrow(
                () -> new RuntimeException("ID not found")
        );
        if(!StringUtils.isEmpty(dto.getName())) {
            s.setName(dto.getName());
        }
        if(!StringUtils.isEmpty(dto.getValue())) {
            s.setValue(dto.getValue());
        }
        return getTimeSheet(timeSheetsId);
    }

    private TimeSheetDto getTimeSheet(Long id) {
        TimeSheet ts = timeSheetRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Time Sheet not found")
        );
        return TimeSheetService.mapper.fromEntity(ts);
    }

    public void deleteSetting(Long id) {
        repository.deleteById(id);
    }

}
