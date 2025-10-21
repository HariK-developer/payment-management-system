package com.smartpay.entity;

import com.smartpay.entity.Enums.PaymentType;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment_methods")
public class PaymentMethods {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "payment method name is required")
    @Column(nullable = false,unique = true)
    private String paymentMethodName;

    @NotNull(message = "payment type is required")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType ;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    private Instant  updatedAt ;

    @OneToMany(mappedBy = "paymentMethod",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Transactions> transactions;
}
