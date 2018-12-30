package com.example.textbook.repository;

import com.example.textbook.domain.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassesRepository extends JpaRepository<Classes, Integer> {
    Classes findClassesByClassName(String name);

    Classes findClassesByClassCode(Integer code);
}
