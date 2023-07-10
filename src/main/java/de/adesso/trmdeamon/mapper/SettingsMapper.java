package de.adesso.trmdeamon.mapper;

import de.adesso.trmdeamon.dto.setting.SettingCreateDto;
import de.adesso.trmdeamon.dto.setting.SettingReadDto;
import de.adesso.trmdeamon.model.Setting;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettingsMapper {

    public Setting fromCreateDto(SettingCreateDto dto) {
        return Setting.builder()
                .name(dto.getName())
                .value(dto.getValue())
                .build();
    }

    public SettingReadDto toReadDto(Setting ts) {
        return SettingReadDto.builder()
                .id(ts.getId())
                .name(ts.getName())
                .value(ts.getValue())
                .build();
    }

    public List<SettingReadDto> listToReadDto(List<Setting> list) {
        return list.stream().map(this::toReadDto).toList();
    }
}
