package com.example.banking.DTO;

import com.example.banking.entities.Transaction;
import com.example.banking.entities.TransactionType;
import com.example.banking.entities.User;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.banking.entities.Transaction}
 */
@Getter
@Setter
@Builder
public class TransactionDto implements Serializable {
    @NotNull(message = "ID_CANNO_BE_NULL")
    @NotEmpty
    @NotBlank
    Integer id;
    @NotNull
    @NotEmpty
    @Positive(message = "AMOUNT_CANNOT_BE_NEGATIVE")
    @Max(value = 9999999 ,message = "MAX_9999999")
    @Min(value = 1 , message = "MIN_1")
    BigDecimal amount;
    TransactionType type;
    @NotNull(message = "DESTINATION_IBAN_CANNO_BE_NULL")
    @NotEmpty(message = "DESTINATION_IBAN_CANNO_BE_EMPTY")
    @NotBlank(message = "DESTINATION_IBAN_CANNO_BE_BLANK")
    String destinationIban;

    Integer idUser;

    public static Transaction toEntity(TransactionDto transaction){
         if(transaction == null ) return null;
         return Transaction
                 .builder()
                 .id(transaction.getId())
                 .amount(transaction.getAmount())
                 .type(transaction.getType())
                 .destinationIban(transaction.getDestinationIban())
                 .user(User.builder().id(transaction.getId()).build())
                 .build();
    }


    public static TransactionDto fromEntity(Transaction transaction){
        if(transaction == null ) return null;
        return TransactionDto
                .builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .destinationIban(transaction.getDestinationIban())
                .idUser(transaction.getUser().getId())
                .build();
    }

}