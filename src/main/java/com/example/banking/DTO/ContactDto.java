package com.example.banking.DTO;

import com.example.banking.entities.Contact;
import com.example.banking.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.banking.entities.Contact}
 */
@Getter
@Setter
@Builder
public class ContactDto  {
    Integer id;
    @NotNull(message = "FIRSTNAME_CANNOT_BE_NULL")
    @NotEmpty(message = "FIRSTNAME_CANNOT_BE_EMPTY")
    @NotBlank(message = "FIRSTNAME_CANNOT_BE_BLANK")
    String firsName;
    @NotNull(message = "LASTNAME_CANNOT_BE_NULL")
    @NotEmpty(message = "LASTNAME_CANNOT_BE_EMPTY")
    @NotBlank(message = "LASTNAME_CANNOT_BE_BLANK")
    String lastName;
    @NotNull(message = "EMAIL_CANNOT_BE_NULL")
    @NotEmpty(message = "EMAIL_CANNOT_BE_EMPTY")
    @NotBlank(message = "EMAIL_CANNOT_BE_BLANK")
    @Email(message = "EMAIL_NOT_VALID")
    String email;
    @NotNull(message = "IBAN_CANNOT_BE_NULL")
    @NotEmpty(message = "IBAN_CANNOT_BE_EMPTY")
    @NotBlank(message = "IBAN_CANNOT_BE_BLANK")
    String iban;
    Integer idUser;

    public static Contact toEntity(ContactDto contact){
         if(contact == null) return null;
         return Contact
                 .builder()
                 .id(contact.getId())
                 .firsName(contact.getFirsName())
                 .lastName(contact.getLastName())
                 .email(contact.getEmail())
                 .iban(contact.getIban())
                 .user(User.builder().id(contact.getIdUser()).build())
                 .build();
    }

    public static ContactDto fromEntity(Contact contact){
        if(contact == null) return null;
        return ContactDto
                .builder()
                .id(contact.getId())
                .firsName(contact.getFirsName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .idUser(contact.getUser().getId())
                .build();
    }
}