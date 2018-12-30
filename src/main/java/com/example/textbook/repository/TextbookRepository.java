package com.example.textbook.repository;

import com.example.textbook.domain.Textbook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextbookRepository extends JpaRepository<Textbook, Integer> {

    Textbook findTextbookByTextbookName(String name);

    Textbook findTextbookByTextbookCode(Integer code);
}
