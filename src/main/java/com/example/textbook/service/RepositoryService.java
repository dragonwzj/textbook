package com.example.textbook.service;

import com.example.textbook.domain.Repository;
import com.example.textbook.repository.RepositoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RepositoryService {

    @Autowired
    private RepositoryRepository repositoryRepository;

    public List<Repository> findAllRepository() {
        return repositoryRepository.findAll();
    }

    public boolean createRepository(Repository repository) {
        try {
            repositoryRepository.save(repository);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Repository findRepositoryByRepositoryCode(Integer code) {
        return repositoryRepository.findRepositoryByRepositoryCode(code);
    }
}
