package com.example.banking.services.implementationsServices;

import com.example.banking.DTO.AccountDto;
import com.example.banking.DTO.UserDto;
import com.example.banking.Repositories.UserRepository;
import com.example.banking.entities.User;
import com.example.banking.helpers.ObjectValidator;
import com.example.banking.services.AccountService;
import com.example.banking.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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

        // Vérification d'unicité de l'email
        if (userRepository.existsByEmailAndIdNot(objDto.getEmail(), objDto.getId())) {
            throw new IllegalStateException("A user already exists with the provided email.");
        }

        // Sauvegarde de l'utilisateur
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
        return userRepository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id=" + id));
    }

    @Override
    public void delete(Integer id) {
        // TODO: Ajouter des vérifications métier avant suppression
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id=" + id);
        }
        userRepository.deleteById(id);
    }
    @Transactional
    @Override
    public Integer validateAccount(Integer id) {
        // Charger l'utilisateur depuis la base
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user was found for validation account! id=" + id));

        // Vérification d'unicité de l'email avant de sauvegarder
        if (userRepository.existsByEmailAndIdNot(user.getEmail(), id)) {
            throw new IllegalStateException("A user already exists with the provided email.");
        }

        // Mettre à jour l'état de l'utilisateur
        user.setActive(true);

        // Sauvegarder les modifications
        User userUpdated = userRepository.save(user);

        // Créer un compte associé
        AccountDto accountDto = AccountDto.builder().user(UserDto.fromEntity(userUpdated)).build();
        accountService.save(accountDto);

        return userUpdated.getId();
    }

    @Override
    public Integer invalidateAccount(Integer id) {
        // Charger l'utilisateur depuis la base
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user was found to invalidate account! id=" + id));

        // Mettre à jour l'état de l'utilisateur
        user.setActive(false);

        // Sauvegarder les modifications
        userRepository.save(user);

        return user.getId();
    }
}
