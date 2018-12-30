package com.example.textbook.repository;

import com.example.textbook.domain.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryRepository extends JpaRepository<Repository, Integer> {
    Repository findRepositoryByRepositoryName(String name);

    Repository findRepositoryByRepositoryCode(Integer code);
}
