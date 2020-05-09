package com.example.repository;

import com.example.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findAll();

    List<Account> findByDate(Date date);

//    Account getOne(Integer id);
    Optional<Account> findById(Integer id);
}
