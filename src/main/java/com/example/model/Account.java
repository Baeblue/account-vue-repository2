package com.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date date;
    private String category;
    private String content;
    private String method;
    private Integer price;

    public Account(String category, String content, String method, Integer price) {
        this.date = new Date();
        this.category = category;
        this.content = content;
        this.method = method;
        this.price = price;
    }
}
