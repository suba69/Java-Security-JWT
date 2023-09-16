package com.example.security1337.service.impl;

import com.example.security1337.dto.UserRegistrationDto;
import com.example.security1337.entity.Role;
import com.example.security1337.entity.User;
import com.example.security1337.mapper.UserMapper;
import com.example.security1337.repository.RoleRepository;
import com.example.security1337.repository.UserRepository;
import com.example.security1337.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.security1337.utils.Constants.ROLE_ADMIN;
import static com.example.security1337.utils.Constants.ROLE_USER;

@Service
@Data
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    @Override
    public void save(UserRegistrationDto userRegistrationDto) {
       userRegistrationDto.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
       User user = userMapper.convertToDomain(userRegistrationDto);
       Optional<Role> optionalRole = roleRepository.findByName(ROLE_USER);
       if (optionalRole.isPresent()) {
           user.setRole(optionalRole.get());
           userRepository.save(user);
       } else {
           throw new DataSourceLookupFailureException("Не может быть обработано");
       }
    }
}
