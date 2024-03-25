package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    @Column(
            nullable = false
    )
    private String lastName;
    @Column(
            nullable = false
    )
    private String firstName;
    @Column(unique = true ,
    nullable = false)
    private String gmail;
    @Column(
            nullable = false
    )
    private String password;
    @Column(
            nullable = false
    )
    private String role;
    @Column(
            nullable = false
    )
    private LocalDate birthdate;


 }
