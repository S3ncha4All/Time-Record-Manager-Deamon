package de.adesso.trmdeamon.service;

import de.adesso.trmdeamon.dto.setting.SettingCreateDto;
import de.adesso.trmdeamon.dto.setting.SettingReadDto;
import de.adesso.trmdeamon.dto.setting.SettingUpdateDto;
import de.adesso.trmdeamon.mapper.SettingsMapper;
import de.adesso.trmdeamon.model.Setting;
import de.adesso.trmdeamon.model.TimeSheet;
import de.adesso.trmdeamon.repository.SettingsRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SettingService {

    private final SettingsMapper mapper;
    private final SettingsRepository repository;
    private final TimeSheetService timeSheetService;

    public SettingReadDto createSetting(SettingCreateDto dto) {
        Setting s = mapper.fromCreateDto(dto);
        TimeSheet ts = timeSheetService.getTimeSheet(dto.getTimeSheetId());
        s.setTimeSheet(ts);
        return mapper.toReadDto(repository.save(s));
    }

    public SettingReadDto updateSetting(SettingUpdateDto dto) {
        Setting s = getSetting(dto.getId());
        if(!StringUtils.isEmpty(dto.getName())) {
            s.setName(dto.getName());
        }
        if(!StringUtils.isEmpty(dto.getValue())) {
            s.setValue(dto.getValue());
        }
        return mapper.toReadDto(repository.save(s));
    }

    public void deleteSetting(Long id) {
        repository.deleteById(id);
    }

    public SettingReadDto getSettingDto(Long id) {
        return mapper.toReadDto(getSetting(id));
    }

    public List<SettingReadDto> getAllSettings(Long timeSheetId) {
        if(timeSheetId != null) {
            return mapper.listToReadDto(repository.findAllSettingsForTimeSheet(timeSheetId));
        } else {
            return mapper.listToReadDto(repository.findAll());
        }
    }

    public Setting getSetting(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Setting not found")
        );
    }
}
