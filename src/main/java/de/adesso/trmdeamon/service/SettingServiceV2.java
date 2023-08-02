package de.adesso.trmdeamon.service;

import de.adesso.trmdeamon.mapper.SettingsMapper;
import de.adesso.trmdeamon.model.Setting;
import de.adesso.trmdeamon.repository.SettingsRepository;
import de.adesso.trmdeamon.util.MakePageRequest;
import de.adesso.trmdeamon.util.sort.SortOrder;
import de.adesso.trmdeamon.util.sort.SortSettings;
import de.adesso.trmdeamon.dto.setting.SettingReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SettingServiceV2 {

    private final SettingsMapper mapper;
    private final SettingsRepository repository;

    public Page<SettingReadDto> getPagedSettings(Long timeSheetId, Integer pageIndex, Integer pageSize, SortSettings sortTimeSheets, SortOrder sortOrder) {
        Pageable pr = MakePageRequest.make(pageIndex, pageSize, sortTimeSheets, sortOrder);
        Page<Setting> page;
        if(timeSheetId != null) {
            page = repository.findAllSettingsForTimeSheet(timeSheetId, pr);
        } else {
            page = repository.findAll(pr);
        }
        return mapper.pageToReadDto(page);
    }
}
