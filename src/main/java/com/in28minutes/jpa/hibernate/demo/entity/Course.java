package com.in28minutes.jpa.hibernate.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@NamedQueries(value = {
        @NamedQuery(name = "query_get_all_courses", query = "select c from Course c"),
        @NamedQuery(name = "query_get_all_courses_join_fetch`   ", query = "select c from Course c JOIN FETCH c.students s"),
        @NamedQuery(name = "query_get_100_step_courses", query = "select c from Course c where name like '%100 Steps'")
})
@Cacheable
@SQLDelete(sql = "update course set is_deleted = true where id=?")
@Where(clause = "is_deleted = false")
public class Course {

    @Id
    @Getter
    @GeneratedValue
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @OneToMany(mappedBy = "course")
    private List<Review> reviews = new ArrayList<>();

    @Getter
    @Setter
    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp
    private LocalDateTime createdDate;

    private boolean isDeleted;

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
    }

    public void addStudents(Student course) {
        this.students.add(course);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
