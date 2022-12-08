package ru.skillbox.dto;

import lombok.Data;
import ru.skillbox.model.Sex;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UserDto {
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
    private Set<UserDto> subscriptions;
    private Set<UserDto> subscribers;
    private boolean deleted;
}
