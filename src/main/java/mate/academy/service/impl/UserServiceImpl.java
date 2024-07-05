package mate.academy.service.impl;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.user.UserRegistrationRequestDto;
import mate.academy.dto.user.UserResponseDto;
import mate.academy.exception.EntityNotFoundException;
import mate.academy.exception.RegistrationException;
import mate.academy.mapper.UserMapper;
import mate.academy.model.Role;
import mate.academy.model.User;
import mate.academy.repository.role.RoleRepository;
import mate.academy.repository.user.UserRepository;
import mate.academy.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.existsByEmail(requestDto.email())) {
            throw new RegistrationException(
                    "The user with the email is already registered: " + requestDto.email());
        }
        User user = userMapper.toEntity(requestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(Role.RoleName.ROLE_USER).orElseThrow(() ->
                new EntityNotFoundException("Can't find the USER role"));
        user.setRoles(Set.of(userRole));
        return userMapper.toDto(
                userRepository.save(user));
    }
}
