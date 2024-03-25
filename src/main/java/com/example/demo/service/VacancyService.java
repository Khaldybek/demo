package com.example.demo.service;

import com.example.demo.dto.VacancyDto;
import com.example.demo.entity.Vacancy;
import com.example.demo.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VacancyService {
    private final VacancyRepository vacancyRepository;
    private  final UserService company;
    @Autowired
    public VacancyService(VacancyRepository vacancyRepository, UserService company) {
        this.vacancyRepository = vacancyRepository;
        this.company = company;
    }

    public List<Vacancy> getVacancy(){
        return vacancyRepository.findAll();

    }
    public String addVacancy(VacancyDto dto){
        Vacancy vacancy=new Vacancy();
        vacancy.setCompany(company.getById(dto.getCompany_id()));
        vacancy.setName(dto.getName());
        vacancy.setDescp(dto.getDescp());
        vacancy.setSalaryA(dto.getSalaryA());
        vacancy.setSalaryB(dto.getSalaryB());
        vacancy.setDate(dto.getDate());
        vacancy.setLocation(dto.getLocation());
        vacancy.setExperience(dto.getExperience());

        vacancyRepository.save(vacancy);
        return "Added"+dto.getName()+""+vacancy.getName();

    }

    public List<Vacancy> getByName(String name){
        return vacancyRepository.findAllByName(name);
    }
    public String updateVacancy(Vacancy vacancy){
        vacancyRepository.save(vacancy);
        return "succes";
    }
}
