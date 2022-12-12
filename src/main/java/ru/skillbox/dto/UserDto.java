package ru.skillbox.dto;

import lombok.Data;
import ru.skillbox.model.Sex;
import ru.skillbox.validation.OnCreateUser;
import ru.skillbox.validation.OnUpdateUser;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.Set;

@Data
public class UserDto {
    @NotNull(groups = OnUpdateUser.class)
    @Null(groups = OnCreateUser.class)
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Sex sex;
    private LocalDate birthday;
    private CityDto city;
    private Set<AvatarDto> avatars;
    private String nickName;
    private Set<HardSkillDto> hardSkills;
    private String email;
    private String phoneNumber;
    private boolean deleted;
}
