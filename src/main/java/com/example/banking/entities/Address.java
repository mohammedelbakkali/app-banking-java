package com.example.banking.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address extends AbstractEntity{

    private String street;
    private Integer houseNumber;
    private Integer zipcode;
    private String city;
    private String country;
    @OneToOne(mappedBy = "address")
    private User user;
}
