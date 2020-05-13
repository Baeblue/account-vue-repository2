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

    @GetMapping("/list")
    public List<Account> getList() {
        System.out.println("Get a List...");

        List<Account> list = repository.findAll();

        // 현재 /년/월/에 충족되는 account 만 filter 해서 프론트에 전달
        TimeZone tz = TimeZone.getTimeZone("Asia/Seoul");
        Calendar cal = Calendar.getInstance(tz);
        System.out.println(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.DATE));

        List<Account> filteredList = list.stream()
                .filter(a -> a.getYearMonth().equals(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1)))
                .collect(Collectors.toList());

        filteredList.sort((a1, a2) -> a2.getDate().compareTo(a1.getDate()));

        return filteredList;
    }

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
        List<Account> list = repository.findAll();

        String selectedYear = selectedDate.getYear();
        String selectedMonth = selectedDate.getMonth();
        System.out.println(selectedYear + "-" + selectedMonth);

        List<Account> selectedList = list.stream()
        .filter(a -> a.getYearMonth().equals(selectedYear + "-" + selectedMonth))
        .collect(Collectors.toList());

        selectedList.sort((a1, a2) -> a2.getDate().compareTo(a1.getDate()));

        return selectedList;
    }
}
