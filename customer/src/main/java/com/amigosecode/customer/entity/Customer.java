package com.amigosecode.customer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer", uniqueConstraints = {
        @UniqueConstraint(name = "customer_email_unique_constraint", columnNames ="email" )
})
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_sequence",
    sequenceName = "customer_sequence",
    allocationSize = 1)
    @GeneratedValue(generator = "customer_sequence",strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;
    @Column(name = "first_name", nullable = false, updatable = true, columnDefinition = "TEXT")
    private String firstName;
    @Column(name = "last_name", nullable = false, updatable = true, columnDefinition = "TEXT")
    private String lastName;
    private String email;
}
