package ru.skillbox.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.skillbox.dto.HardSkillDto;
import ru.skillbox.model.HardSkill;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HardSkillMapper extends AbstractMapper<HardSkillDto, HardSkill> {
}
