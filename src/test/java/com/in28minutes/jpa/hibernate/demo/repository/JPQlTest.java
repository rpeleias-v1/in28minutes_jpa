package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class JPQlTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    public void findById_basic() {
        List resultList = entityManager.createQuery("select c from Course c").getResultList();
        System.out.println("Result list: " + resultList);
    }

    @Test
    public void findById_typed() {
        TypedQuery<Course> query = entityManager.createNamedQuery("query_get_all_courses", Course.class);
        List<Course> resultList = query.getResultList();
        System.out.println("Result list: " + resultList);
    }

    @Test
    public void findById_where() {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c where name like '%100 Steps%'", Course.class);
        List<Course> resultList = query.getResultList();
        System.out.println("Result list: " + resultList);
    }

}
