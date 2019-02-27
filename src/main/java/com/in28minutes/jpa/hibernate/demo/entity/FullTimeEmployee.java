package com.in28minutes.jpa.hibernate.demo.entity;

import lombok.Getter;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class FullTimeEmployee extends Employee {

    public FullTimeEmployee() {
    }

    public FullTimeEmployee(String name, BigDecimal salary) {
        super(name);
        this.salary = salary;
    }

    @Getter
    private BigDecimal salary;
}
