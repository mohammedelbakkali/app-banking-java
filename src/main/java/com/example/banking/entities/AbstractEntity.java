package com.example.banking.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
// Data JPA's auditing mechanism
//  you can efficiently handle creation and modification metadata without manual effort
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @CreatedDate
    @Column(
            name = "createdDate"
        //     nullable = false,
        //     updatable = false
    )
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(
            name = "lastModifiedDate"
    )
    private LocalDateTime lastModifiedDate;
}
