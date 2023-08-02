package de.adesso.trmdeamon.v2.service;

import de.adesso.trmdeamon.util.sort.SortOrder;
import de.adesso.trmdeamon.util.sort.SortSettings;
import de.adesso.trmdeamon.util.sort.SortTimeSheets;
import de.adesso.trmdeamon.v1.dto.setting.SettingCreateDto;
import de.adesso.trmdeamon.v1.dto.setting.SettingReadDto;
import de.adesso.trmdeamon.v1.dto.setting.SettingUpdateDto;
import de.adesso.trmdeamon.v1.mapper.SettingsMapper;
import de.adesso.trmdeamon.v1.model.Setting;
import de.adesso.trmdeamon.v1.model.TimeSheet;
import de.adesso.trmdeamon.v1.repository.SettingsRepository;
import de.adesso.trmdeamon.v1.service.TimeSheetService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SettingServiceV2 {

    public Page<SettingReadDto> getPagedSettings(Long timeSheetId, Integer pageIndex, Integer pageSize, SortSettings sortTimeSheets, SortOrder sortOrder) {

        return null;
    }
}
