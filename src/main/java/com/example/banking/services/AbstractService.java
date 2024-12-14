package com.example.banking.services;

import java.util.Set;

public  interface AbstractService<T>{

    Integer save(T objDto);
    Set<T> findAll();
    T findById(Integer id);
    void delete(Integer id);
}
