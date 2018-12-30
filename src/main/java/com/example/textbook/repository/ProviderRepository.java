package com.example.textbook.repository;

import com.example.textbook.domain.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {
    Provider findProviderByProviderName(String name);

    Provider findProviderByProviderCode(Integer code);
}
