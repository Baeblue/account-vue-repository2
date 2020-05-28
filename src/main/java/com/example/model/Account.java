package com.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;
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

    public String getYearMonth() {
        String filteredDate = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.date);
        // 2020-05-11 -> 2020-5
        filteredDate = (cal.get(Calendar.YEAR)) + "-" + (cal.get(Calendar.MONTH) + 1);

        return filteredDate;
    }

    public String searchedYearMonth() {
        String filteredDate = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.date);

        if ((cal.get(Calendar.MONTH) + 1) < 10) {
            // 2020-05-11 -> 2020-05
            filteredDate = (cal.get(Calendar.YEAR)) + "-0" + (cal.get(Calendar.MONTH) + 1);
        } else {
            // 2020-12-11 -> 2020-12
            filteredDate = (cal.get(Calendar.YEAR)) + "-" + (cal.get(Calendar.MONTH) + 1);
        }

        return filteredDate;
    }
}
