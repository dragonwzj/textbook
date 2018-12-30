package com.example.textbook.repository;

import com.example.textbook.domain.Distribution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistributionRepository extends JpaRepository<Distribution, String> {
}
