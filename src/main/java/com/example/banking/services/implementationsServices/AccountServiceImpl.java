package com.example.banking.services.implementationsServices;

import com.example.banking.DTO.AccountDto;
import com.example.banking.Repositories.AccountRepository;
import com.example.banking.entities.Account;
import com.example.banking.helpers.ObjectValidator;
import com.example.banking.services.AccountService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final ObjectValidator<AccountDto> validator;
    @Override
    public Integer save(AccountDto objDto) {
        validator.validate(objDto);
        Account account = AccountDto.toEntity(objDto);
        // todo genrete random Iban
        account.setIban(generateRandomIban());
        return accountRepository.save(account).getId();
    }



    @Override
    public Set<AccountDto> findAll() {
        return accountRepository
                .findAll()
                .stream()
                .map(AccountDto::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public AccountDto findById(Integer id) {
        return accountRepository.findById(id).map(AccountDto::fromEntity).orElseThrow(()-> new EntityNotFoundException("not account was found with the id = "+ id));
    }

    @Override
    public void delete(Integer id) {

    }


    @Override
    public String generateRandomIban() {
        // todo generate Random Iban
        String iban = Iban.random(CountryCode.DE).toFormattedString();
        // check if the iban already exists
        Optional<Account> account=  accountRepository.findByIban(iban);
        if(account.isPresent()){
            // if exists -> generate new random iban
                 generateRandomIban();
        }

        // else return iban
        return iban;
    }
}
