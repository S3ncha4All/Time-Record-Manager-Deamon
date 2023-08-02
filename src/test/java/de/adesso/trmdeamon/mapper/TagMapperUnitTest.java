package de.adesso.trmdeamon.mapper;


import de.adesso.trmdeamon.dto.tag.TagReadDto;
import de.adesso.trmdeamon.model.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TagMapperUnitTest {

    private TagMapper mapper;

    @BeforeEach
    public void init() {
        mapper = new TagMapper();
    }

    @Test
    public void testToReadDto() {
        Long id = 1234L;
        String name = "name";
        Tag t = Tag.builder()
                .id(id)
                .name(name)
                .build();
        TagReadDto dto = mapper.fromEntity(t);
        Assertions.assertEquals(id, dto.getId());
        Assertions.assertEquals(name, dto.getName());
    }
}
