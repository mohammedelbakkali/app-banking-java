package com.example.banking.services;

import com.example.banking.DTO.UserDto;


public interface UserService extends AbstractService<UserDto>{

    Integer validateAccount(Integer id);
    Integer invalidateAccount(Integer id);
}
