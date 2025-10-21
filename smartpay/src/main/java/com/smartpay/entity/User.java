package com.smartpay.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min=2,max=50, message = "Name must be between 2 and 50 characters")
    private  String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private  String email;

    @NotBlank(message = "Password is required")
    @Size(min=4,max=255,message = "Password must be between 4 and 20 characters")
    private  String password;

    @NotBlank(message = "Role is required")
    private String role;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    private Instant  updatedAt ;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Transactions> transactions;

}