package com.example.banking.services.implementationsServices;

import com.example.banking.DTO.AddressDto;
import com.example.banking.Repositories.AddressRepository;
import com.example.banking.entities.Address;
import com.example.banking.helpers.ObjectValidator;
import com.example.banking.services.AddressService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ObjectValidator<AddressDto> validator;

    @Override
    public Integer save(AddressDto objDto) {
        validator.validate(objDto);
        return addressRepository.save(AddressDto.toEntity(objDto)).getId();
    }

    @Override
    public Set<AddressDto> findAll() {

        return  addressRepository.findAll().stream()
                .map(AddressDto::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public AddressDto findById(Integer id) {
        Optional<Address> address = addressRepository.findById(id);
        return address.map(AddressDto::fromEntity).orElseThrow(()-> new EntityNotFoundException("not found Address id = "+id));
    }

    @Override
    public void delete(Integer id) {
        // todo check before delete
    }
}
