package com.example.banking.services.implementationsServices;

import com.example.banking.DTO.AccountDto;
import com.example.banking.DTO.UserDto;
import com.example.banking.Repositories.UserRepository;
import com.example.banking.entities.Account;
import com.example.banking.entities.User;
import com.example.banking.helpers.ObjectValidator;
import com.example.banking.services.AccountService;
import com.example.banking.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final AccountService accountService;
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

    @Override
    public Integer validateAccount(Integer id) {
      User user =  userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("not user was found for validation account ! id="+id));
        user.setActive(true);
        AccountDto accountDto = AccountDto.builder().user(UserDto.fromEntity(user)).build();
        accountService.save(accountDto);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Integer invalidateAccount(Integer id) {
        User user =  userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("not user was found for validation account ! id="+id));
        user.setActive(false);
        userRepository.save(user);
        return user.getId();
    }
}
