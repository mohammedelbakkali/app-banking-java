package com.example.banking.DTO;


import com.example.banking.entities.User;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    @NotNull(message = "FIRSTNAME_CANNOT_BE_NULL")
    @NotEmpty(message = "FIRSTNAME_CANNOT_BE_EMPTY")
    @NotBlank(message = "FIRSTNAME_CANNOT_BE_BLANK")
    private String firsName;
    @NotNull(message = "LASTNAME_CANNOT_BE_NULL")
    @NotEmpty(message = "LASTNAME_CANNOT_BE_EMPTY")
    @NotBlank(message = "LASTNAME_CANNOT_BE_BLANK")
    private String lastName;
    @NotNull(message = "EMAIL_CANNOT_BE_NULL")
    @NotEmpty(message = "EMAIL_CANNOT_BE_EMPTY")
    @NotBlank(message = "EMAIL_CANNOT_BE_BLANK")
    @Email(message = "EMAIL_NOT_VALID")
    private String email;
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 8 , max = 30 , message = "PASSWORD_NOT_VALID")
    private String password;
    private AddressDto address;
    @Past
    private LocalDateTime date;



    public static User toEntity(UserDto userDto){
         return User.builder()
                 .firsName(userDto.getFirsName())
                 .lastName(userDto.getLastName())
                 .email(userDto.getEmail())
                 .address(AddressDto.toEntity(userDto.getAddress()))
                 .password(userDto.getPassword())
                 .build();
    }

    public static UserDto fromEntity(User user){
        return  builder()
                .firsName(user.getFirsName())
                .lastName(user.getLastName())
                .address(AddressDto.fromEntity(user.getAddress()))
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }


}
