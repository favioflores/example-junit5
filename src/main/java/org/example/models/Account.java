package org.example.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Account {

    private String personName;
    private BigDecimal balance;
    private Integer age;

    public void setPersonName(String personName) {

        String strName = null;

        if (personName != null) {
            strName = personName.trim().toUpperCase();
        } else {
            throw new NullPointerException("Name cannot be null");
        }

        this.personName = strName;
    }

    public void setAge(Integer age) {

        if (age >= 18){
            this.age = age;
        }

    }
}
