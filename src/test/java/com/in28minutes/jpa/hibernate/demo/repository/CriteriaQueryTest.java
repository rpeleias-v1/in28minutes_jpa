package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@Transactional
public class CriteriaQueryTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    public void all_courses() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);

        TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
        List resultList = query.getResultList();
        System.out.println("Result list: " + resultList);
    }

    @Test
    public void all_courses_having_100_steps() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);
        Predicate like100Steps = cb.like(courseRoot.get("name"), "%100 Steps");
        cq.where(like100Steps);

        TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
        List resultList = query.getResultList();
        System.out.println("Result list: " + resultList);
    }

    @Test
    public void all_courses_without_students() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);
        Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));
        cq.where(studentsIsEmpty);

        TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
        List resultList = query.getResultList();
        System.out.println("Result list: " + resultList);
    }

    @Test
    public void join() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);
        courseRoot.join("students");

        TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
        List resultList = query.getResultList();
        System.out.println("Result list: " + resultList);
    }

    @Test
    public void left_join() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> courseRoot = cq.from(Course.class);
        courseRoot.join("students", JoinType.LEFT);

        TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
        List resultList = query.getResultList();
        System.out.println("Result list: " + resultList);
    }

}
