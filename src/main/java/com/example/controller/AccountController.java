package com.example.controller;

import com.example.model.Account;
import com.example.model.SelectedDate;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountRepository repository;

    @PostMapping("/account")
    public Account addLine(@RequestBody Account account) {
        Account _account = repository.save(
            new Account(
                account.getCategory(),
                account.getContent(),
                account.getMethod(),
                account.getPrice()
            )
        );
        return _account;
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<String> deleteLine(@PathVariable("id") Integer id) {
        System.out.println("Delete the Line with ID = " + id + "...");

        repository.deleteById(id);

        return new ResponseEntity<>("The Line has been deleted!", HttpStatus.OK);
    }

    @GetMapping("/account/{id}")
    public Optional<Account> getOne(@PathVariable Integer id) {
        return repository.findById(id);
    }

    @PutMapping("/account/{id}")
    public ResponseEntity<Account> updateLine(@PathVariable Integer id, @RequestBody Account account) {
        System.out.println("Update the Line with ID = " + id + "...");

        Optional<Account> accountData = repository.findById(id);

        if (accountData.isPresent()) {
            Account _account = accountData.get();
            _account.setCategory(account.getCategory());
            _account.setContent(account.getContent());
            _account.setMethod(account.getMethod());
            _account.setPrice(account.getPrice());
            return new ResponseEntity<>(repository.save(_account), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/monthly")
    public List<Account> findByDate(@RequestBody SelectedDate selectedDate) {
        System.out.println("Get a List...");

        List<Account> list = repository.findAll();

        String selectedYear = selectedDate.getYear();
        String selectedMonth = selectedDate.getMonth();
        System.out.println(selectedYear + "-" + selectedMonth);

        // 원하는 달의 리스트만 filter해서 Front에 전달
        List<Account> selectedList = list.stream()
        .filter(a -> a.searchedYearMonth().equals(selectedYear + "-" + selectedMonth))
        .collect(Collectors.toList());

        // 나중에 입력한 값이 맨 위에 오도록 sort를 이용해 재정렬
        selectedList.sort((a1, a2) -> a2.getId().compareTo(a1.getId()));

        return selectedList;
    }
}
