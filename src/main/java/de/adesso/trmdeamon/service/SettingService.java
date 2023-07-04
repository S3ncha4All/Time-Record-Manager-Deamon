package de.adesso.trmdeamon.service;

import de.adesso.trmdeamon.dto.settings.ConstructSettingDto;
import de.adesso.trmdeamon.dto.settings.SettingDto;
import de.adesso.trmdeamon.dto.settings.UpdateSettingDto;
import de.adesso.trmdeamon.dto.timesheet.TimeSheetDto;
import de.adesso.trmdeamon.mapper.Mapper;
import de.adesso.trmdeamon.model.Setting;
import de.adesso.trmdeamon.model.TimeSheet;
import de.adesso.trmdeamon.repository.SettingsRepository;
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

    public static final Mapper<Setting, SettingDto> settingMapper = new Mapper<>() {
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
    private final TimeSheetService timeSheetService;

    public TimeSheetDto createSetting(Long timeSheetId, ConstructSettingDto dto) {
        TimeSheet ts = timeSheetService.getTimeSheet(timeSheetId);
        Setting s = constructionMapper.fromDto(dto);
        s.setTimeSheet(ts);
        repository.save(s);
        return timeSheetService.getTimeSheetDto(timeSheetId);
    }

    public TimeSheetDto updateSetting(Long timeSheetId, Long settingId, UpdateSettingDto dto) {
        Setting s = repository.findById(settingId).orElseThrow(
                () -> new RuntimeException("Setting not found")
        );
        if(!StringUtils.isEmpty(dto.getName())) {
            s.setName(dto.getName());
        }
        if(!StringUtils.isEmpty(dto.getValue())) {
            s.setValue(dto.getValue());
        }
        return timeSheetService.getTimeSheetDto(timeSheetId);
    }

    public TimeSheetDto deleteSetting(Long timeSheetId, Long id) {
        repository.deleteById(id);
        return timeSheetService.getTimeSheetDto(timeSheetId);
    }

}
