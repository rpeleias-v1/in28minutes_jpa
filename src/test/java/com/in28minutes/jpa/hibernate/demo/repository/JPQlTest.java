package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@Transactional
public class JPQlTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    public void findById_basic() {
        List resultList = entityManager.createQuery("select c from Course c").getResultList();
        System.out.println("Result list: " + resultList);
    }

    @Test
    public void jpql_typed() {
        TypedQuery<Course> query = entityManager.createNamedQuery("query_get_all_courses", Course.class);
        List<Course> resultList = query.getResultList();
        System.out.println("Result list: " + resultList);
    }

    @Test
    public void jpql_where() {
        TypedQuery<Course> query = entityManager.createNamedQuery("query_get_100_step_courses", Course.class);
        List<Course> resultList = query.getResultList();
        System.out.println("Result list: " + resultList);
    }

    @Test
    public void jpql_courses_without_students() {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c where c.students is empty", Course.class);
        List<Course> resultList = query.getResultList();
        System.out.println("Result list: " + resultList);
    }

    @Test
    public void jpql_courses_with_at_least_2_students() {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c where size(c.students) >= 2", Course.class);
        List<Course> resultList = query.getResultList();
        System.out.println("Result list: " + resultList);
    }

    @Test
    public void jpql_courses_with_ordered_by_students() {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c order by size(c.students)", Course.class);
        List<Course> resultList = query.getResultList();
        System.out.println("Result list: " + resultList);
    }

    @Test
    public void jpql_students_with_passports_in_certain_pattern() {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s where s.passport.number like '%1234%'", Student.class);
        List<Student> resultList = query.getResultList();
        System.out.println("Result list: " + resultList);
    }

    @Test
    public void join() {
        Query query = entityManager.createQuery("select c, s from Course c JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        System.out.println("Results size: " + resultList.size());
        resultList.forEach(result -> System.out.println(result[0] + " " + result[1]));
    }

    @Test
    public void left_join() {
        Query query = entityManager.createQuery("select c, s from Course c LEFT JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        System.out.println("Results size: " + resultList.size());
        resultList.forEach(result -> System.out.println(result[0] + " " + result[1]));
    }

    @Test
    public void cross_join() {
        Query query = entityManager.createQuery("select c, s from Course c, Student s");
        List<Object[]> resultList = query.getResultList();
        System.out.println("Results size: " + resultList.size());
        resultList.forEach(result -> System.out.println(result[0] + " " + result[1]));
    }
}
