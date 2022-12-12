package ru.skillbox.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.skillbox.dto.HardSkillDto;
import ru.skillbox.model.HardSkill;

import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { HardSkillMapper.class }
)
public interface HardSkillSetMapper extends AbstractMapper<Set<HardSkillDto>, Set<HardSkill>> {
}
