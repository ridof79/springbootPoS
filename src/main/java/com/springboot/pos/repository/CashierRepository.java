package com.springboot.pos.repository;

import com.springboot.pos.model.Cashier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CashierRepository extends JpaRepository<Cashier, String> {
    Cashier findCashierByUsername(String username);
    Optional<Cashier> findByUsername(String username);

}
