package com.cycles.rest.repository;

import com.cycles.rest.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long>{
    
}
