package com.in28minutes.jpa.hibernate.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@ToString
public class Course {

    @Id
    @Getter
    @GeneratedValue
    private Long id;

    @Getter
    @Setter
    private String name;

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }
}
