package com.example.banking.services.implementationsServices;

import com.example.banking.DTO.ContactDto;
import com.example.banking.Repositories.ContactRepository;
import com.example.banking.entities.Contact;
import com.example.banking.helpers.ObjectValidator;
import com.example.banking.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ObjectValidator<ContactDto> validator;

    @Override
    public Integer save(ContactDto objDto) {
        validator.validate(objDto);
        return contactRepository.save(ContactDto.toEntity(objDto)).getId();
    }

    @Override
    public Set<ContactDto> findAll() {
        return contactRepository.findAll()
                .stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public ContactDto findById(Integer id) {
        Optional<Contact> contact = contactRepository.findById(id);
        return contact.map(ContactDto::fromEntity).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        // todo check before delete
    }
}
