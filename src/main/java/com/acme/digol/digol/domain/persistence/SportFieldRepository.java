package com.acme.digol.digol.domain.persistence;

import com.acme.digol.digol.domain.model.entity.SportField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportFieldRepository extends JpaRepository<SportField, Long> {
    List<SportField> findAllByPrice(float price);
    SportField findByName(String name);
}
