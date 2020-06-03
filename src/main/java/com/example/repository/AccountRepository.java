package com.example.repository;

import com.example.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findAll();

    Optional<Account> findById(Integer id);
}
