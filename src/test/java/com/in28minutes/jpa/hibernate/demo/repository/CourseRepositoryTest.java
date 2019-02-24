package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CourseRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void findById_basic() {
        Course course = courseRepository.findById(10001L);

        Assert.assertEquals("JPA in 50 steps", course.getName());
    }
}
