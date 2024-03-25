package com.example.demo.controller;

import com.example.demo.dto.VacancyDto;
import com.example.demo.entity.User;
import com.example.demo.entity.Vacancy;
import com.example.demo.repository.VacancyRepository;
import com.example.demo.service.UserService;
import com.example.demo.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vacancy")
public class VacancyController {
    private final VacancyService vacancyService;

    @Autowired
    public VacancyController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }
    @GetMapping

    public List<Vacancy> getVacancies(){
        return vacancyService.getVacancy();
    };
    @PostMapping("/add_group")
    public String getVacancies(@RequestBody  VacancyDto dto){
        return vacancyService.addVacancy(dto);
    };
    @GetMapping("/byname/{name}")
    public List<Vacancy> getVacanciesByName( @PathVariable  String name){
        return vacancyService.getByName(name);
    }
    @PutMapping("/update")
    public  String updateVacancy(Vacancy vac){
        return vacancyService.updateVacancy(vac);
    }

}
