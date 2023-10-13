package com.cycles.rest.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.cycles.rest.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    public Optional<User> findByName(String name);

    Boolean existsByName(String name);
}