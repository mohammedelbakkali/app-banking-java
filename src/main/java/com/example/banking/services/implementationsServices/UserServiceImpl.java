package com.example.banking.services.implementationsServices;

import com.example.banking.DTO.UserDto;
import com.example.banking.Repositories.UserRepository;
import com.example.banking.entities.User;
import com.example.banking.helpers.ObjectValidator;
import com.example.banking.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final ObjectValidator<UserDto> validator;

    @Override
    public Integer save(UserDto objDto) {
        validator.validate(objDto);
        return userRepository.save(UserDto.toEntity(objDto)).getId();
    }

    @Override
    public Set<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public UserDto findById(Integer id) {
       Optional<User> u = userRepository.findById(id);
        return u.map(UserDto::fromEntity).orElse(null);
    }

    @Override
    public void delete(Integer id) {
         // todo check before delete

    }
}
