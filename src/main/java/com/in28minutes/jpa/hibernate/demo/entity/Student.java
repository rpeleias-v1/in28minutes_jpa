package com.in28minutes.jpa.hibernate.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    private Passport passport;

    public Student(String name) {
        this.name = name;
    }

    public Student() {
    }
}
