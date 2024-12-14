package com.example.banking.DTO;

import com.example.banking.entities.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.example.banking.entities.Address}
 *
 * Encapsulation of Data , decoupling JPA entities or business models from presentation logic
 *
 * isolating and protecting the business models (entities). Its
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AddressDto implements Serializable {
    Integer id;
    @NotNull(message = "street_CANNOT_BE_NULL")
    @NotEmpty(message = "street_CANNOT_BE_EMPTY")
    @NotBlank(message = "street_CANNOT_BE_BLANK")
    String street;
    @NotNull
    Integer houseNumber;
    @NotNull
    Integer zipcode;
    @NotNull(message = "city_CANNOT_BE_NULL")
    @NotEmpty(message = "city_CANNOT_BE_EMPTY")
    @NotBlank(message = "city_CANNOT_BE_BLANK")
    String city;
    @NotNull(message = "country_CANNOT_BE_NULL")
    @NotEmpty(message = "country_CANNOT_BE_EMPTY")
    @NotBlank(message = "country_CANNOT_BE_BLANK")
    String country;


    public static Address toEntity(AddressDto addressDto){
        if(addressDto == null) return null;
         return Address.builder()
                 .id(addressDto.getId())
                 .street(addressDto.getStreet())
                 .houseNumber(addressDto.getHouseNumber())
                 .zipcode(addressDto.getZipcode())
                 .city(addressDto.getCity())
                 .country(addressDto.getCountry())
                 .build();

    }

    public static AddressDto fromEntity(Address address){
         if (address == null) return null;
         return AddressDto.builder()
                 .id(address.getId())
                 .street(address.getStreet())
                 .houseNumber(address.getHouseNumber())
                 .zipcode(address.getZipcode())
                 .city(address.getCity())
                 .country(address.getCountry())
                 .build();
    }

}