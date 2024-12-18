package com.example.banking.DTO;

import com.example.banking.entities.Account;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.hibernate.boot.jaxb.hbm.spi.Adapter7;

import java.io.Serializable;

/**
 * DTO for {@link com.example.banking.entities.Account}
 */
@Builder
@Getter
@Setter
public class AccountDto implements Serializable {
    private Integer id;

    String iban;
    private UserDto user;

    public static AccountDto fromEntity(Account account){
        if(account == null) return null;
         return AccountDto
                 .builder()
                 .id(account.getId())
                 .iban(account.getIban())
                 .user(UserDto.fromEntity(account.getUser()))
                 .build();
    }

    public static Account toEntity(AccountDto accountDto){
        if(accountDto == null) return null;
         return Account
                 .builder()
                 .id(accountDto.getId())
                 .user(UserDto.toEntity(accountDto.getUser()))
                 .iban(accountDto.getIban())
                 .build();
    }
}