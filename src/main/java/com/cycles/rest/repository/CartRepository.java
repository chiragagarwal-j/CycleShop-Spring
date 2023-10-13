package com.cycles.rest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cycles.rest.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserID(int userID);
    List<Cart> findByOrdered(boolean ordered);
    Optional<Cart> findByUserIDAndCycleIdAndOrdered(long userID, long cycleID,boolean ordered);
}
