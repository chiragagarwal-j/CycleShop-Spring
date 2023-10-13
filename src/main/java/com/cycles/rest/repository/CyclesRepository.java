package com.cycles.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cycles.rest.entity.Cycle;

public interface CyclesRepository extends JpaRepository<Cycle, Long> {
    
}
