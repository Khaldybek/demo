package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name ="Vacancies")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vacancy {
    @Id
    @SequenceGenerator(
            name = "vacancy_sequence",
            sequenceName = "vacancy_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vacancy_sequence"
    )
    private Long id;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private User company;
    private String name;
    private String descp ;
    private int salaryA;
    private int salaryB;
    private LocalDate date;

    private String location;
    private  int experience;



}
