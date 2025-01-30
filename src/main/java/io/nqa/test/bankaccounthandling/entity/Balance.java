package io.nqa.test.bankaccounthandling.entity;

import io.nqa.test.bankaccounthandling.configuration.ApplicationProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "balance")
@ToString
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;
    @Column(nullable = false)
    private ApplicationProperties.Currency currency;
    @Column(nullable = false)
    private Double amount = 0d;

    public Balance(ApplicationProperties.Currency currency) {
        this.currency = currency;
    }
}
