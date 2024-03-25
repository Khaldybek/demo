package com.example.demo.dto;

import com.example.demo.entity.User;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VacancyDto {
    private Long company_id;
    private String name;
    private String descp ;
    private int salaryA;
    private int salaryB;
    private LocalDate date;
    private String location;
    private  int experience;

}
