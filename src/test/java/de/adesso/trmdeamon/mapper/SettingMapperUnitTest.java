package de.adesso.trmdeamon.mapper;


import de.adesso.trmdeamon.v1.dto.setting.SettingCreateDto;
import de.adesso.trmdeamon.v1.dto.setting.SettingReadDto;
import de.adesso.trmdeamon.v1.mapper.SettingsMapper;
import de.adesso.trmdeamon.v1.model.Setting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class SettingMapperUnitTest {

    private SettingsMapper mapper;

    @BeforeEach
    public void init() {
        mapper = new SettingsMapper();
    }

    @Test
    public void testFromCreateDto() {
        String name = "name";
        String value = "value";
        Long tsId = 1234L;
        SettingCreateDto dto = SettingCreateDto.builder()
                .name(name)
                .value(value)
                .timeSheetId(tsId)
                .build();
        Setting s = mapper.fromCreateDto(dto);
        Assertions.assertEquals(name, s.getName());
        Assertions.assertEquals(value, s.getValue());
        Assertions.assertNull(s.getTimeSheet());
    }

    @Test
    public void testToReadDto() {
        Long id = 1234L;
        String name = "name";
        String value = "value";
        Setting s = Setting.builder()
                .id(id)
                .name(name)
                .value(value)
                .build();
        SettingReadDto dto = mapper.toReadDto(s);
        Assertions.assertEquals(id, dto.getId());
        Assertions.assertEquals(name, dto.getName());
        Assertions.assertEquals(value, dto.getValue());
    }
}
