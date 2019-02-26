package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class StudentRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager entityManager;

    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }


    public void deleteById(Long id) {
        Course course = findById(id);
        entityManager.remove(course);
    }

    public Course save(Course course) {
        if (course.getId() == null) {
            entityManager.persist(course);
        } else {
            entityManager.merge(course);
        }
        return course;
    }

    public void playWithEntityManager() {
        logger.info("Playing with entityManager -> lets start!!");
        Course course1 = new Course("Web Services in 100 steps");
        entityManager.persist(course1);
        entityManager.flush();
        // transactional context keeps change and track it to DB
        course1.setName("Updated course1");
        entityManager.flush();

        entityManager.refresh(course1);
        Course course2 = new Course("Angular in 100 steps");
        // transactional context keeps change and track it to DB
        course2.setName("Updated Angular course");
        entityManager.flush();
    }
}