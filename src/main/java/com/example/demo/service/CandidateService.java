package com.example.demo.service;

import com.example.demo.dto.Candidatedto;
import com.example.demo.entity.Candidate;
import com.example.demo.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {
    private final CandidateRepository repository;
    @Autowired
    public CandidateService(CandidateRepository repository) {
        this.repository = repository;
    }
    public Candidate createCandidate(Candidatedto candidate) {
        Candidate candidate1=new Candidate();

        return repository.save(candidate1);
    }

    public List<Candidate> getAllCandidates() {
        return repository.findAll();
    }
    public Optional<Candidate> getCandidateById(Long id) {
        return repository.findById(id);
    }

    public Candidate updateCandidate(Candidatedto candidate) {
        Candidate candidate1=new Candidate();

        return repository.save(candidate1) ;
    }

    public void deleteCandidateById(Long id) {
        repository.deleteById(id);
    }

    public List<Candidate> getCandidatesByName(String name) {
        return repository.findByUser_Name(name);
    }
}
