package ru.skillbox.mapper;

import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skillbox.dto.AvatarDto;
import ru.skillbox.dto.CityDto;
import ru.skillbox.dto.HardSkillDto;
import ru.skillbox.dto.UserDto;
import ru.skillbox.model.Sex;
import ru.skillbox.model.User;

import java.time.LocalDate;
import java.time.Month;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {
        AvatarMapperImpl.class,
        AvatarSetMapperImpl.class,
        CityMapperImpl.class,
        HardSkillMapperImpl.class,
        HardSkillSetMapperImpl.class,
        UserMapperImpl.class
})
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void toEntityTest() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setFirstName("Test");
        userDto.setLastName("Testov");
        userDto.setMiddleName("Testovich");
        userDto.setSex(Sex.MALE);
        userDto.setBirthday(LocalDate.of(2000, Month.JANUARY, 1));
        CityDto cityDto = new CityDto();
        cityDto.setId(1L);
        cityDto.setName("N");
        userDto.setCity(cityDto);
        AvatarDto avatarDto1 = new AvatarDto();
        avatarDto1.setFileName("avatar1.jpg");
        AvatarDto avatarDto2 = new AvatarDto();
        avatarDto1.setFileName("avatar2.jpg");
        userDto.setAvatars(Sets.newSet(avatarDto1, avatarDto2));
        userDto.setNickName("test");
        HardSkillDto hardSkillDto1 = new HardSkillDto();
        hardSkillDto1.setId(1L);
        hardSkillDto1.setName("java");
        HardSkillDto hardSkillDto2 = new HardSkillDto();
        hardSkillDto1.setId(2L);
        hardSkillDto1.setName("spring");
        userDto.setHardSkills(Sets.newSet(hardSkillDto1, hardSkillDto2));
        userDto.setEmail("testov@mail.ru");
        userDto.setPhoneNumber("+7(999)99-99-99");
        userDto.setDeleted(true);

        User user = userMapper.toEntity(userDto);

        assertNotNull(user);
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(userDto.getLastName(), user.getLastName());
        assertEquals(userDto.getMiddleName(), user.getMiddleName());
        assertEquals(userDto.getSex(), user.getSex());
        assertEquals(userDto.getBirthday(), user.getBirthday());
        assertNotNull(user.getCity());
        assertNotNull(user.getAvatars());
        assertEquals(2, user.getAvatars().size());
        assertEquals(userDto.getNickName(), user.getNickName());
        assertNotNull(user.getHardSkills());
        assertEquals(2, user.getHardSkills().size());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getPhoneNumber(), user.getPhoneNumber());
        assertEquals(userDto.isDeleted(), user.isDeleted());
    }
}
