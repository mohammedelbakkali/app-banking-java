package com.example.banking.services;

import com.example.banking.DTO.ContactDto;

import java.util.Set;

public interface ContactService extends AbstractService<ContactDto>{
    Set<ContactDto> findAllByUserId(Integer id);
}
