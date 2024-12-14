package com.example.banking.helpers;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectValidator <T>{
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();
    public void validate(T objToValidation){
       Set<ConstraintViolation<T>> violations =   validator.validate(objToValidation);
       if (!violations.isEmpty()){
           Set<String> errorMessages = violations.stream()
                   .map(ConstraintViolation::getMessage)
                   .collect(Collectors.toSet());
           // todo raise an exception !

           throw new ObjectValidationException(errorMessages,objToValidation.getClass().getName());

       }
    }

}
