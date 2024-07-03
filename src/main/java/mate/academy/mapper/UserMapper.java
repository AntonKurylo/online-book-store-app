package mate.academy.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import mate.academy.config.MapperConfig;
import mate.academy.dto.user.UserRegistrationRequestDto;
import mate.academy.dto.user.UserResponseDto;
import mate.academy.model.Role;
import mate.academy.model.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    @Mapping(target = "roles", ignore = true)
    UserResponseDto toDto(User user);

    @AfterMapping
    default void setSkillIds(@MappingTarget UserResponseDto userResponseDto, User user) {
        Set<Long> roleIds = user.getRoles().stream()
                        .map(Role::getId)
                                .collect(Collectors.toSet());
        userResponseDto.setRoles(roleIds);
    }

    @Mapping(target = "roles", ignore = true)
    User toEntity(UserRegistrationRequestDto requestDto);

    @AfterMapping
    default void setSkills(@MappingTarget User user, UserRegistrationRequestDto requestDto) {
        Set<Role> roles = requestDto.roles().stream()
                .map(Role::new)
                .collect(Collectors.toSet());
        user.setRoles(roles);
    }
}
