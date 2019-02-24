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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void findById_basic() {
        Course course = courseRepository.findById(10001L);
        Assert.assertEquals("JPA in 50 steps", course.getName());
    }

    @Test
    @DirtiesContext
    public void deleteById_basic() {
        courseRepository.deleteById(10002L);
        Assert.assertNull("JPA in 50 steps", courseRepository.findById(10002L));
    }

    @Test
    @DirtiesContext
    public void save_basic() {
        Course course = courseRepository.findById(10001L);
        Assert.assertEquals("JPA in 50 steps", course.getName());

        course.setName("Updated course");
        courseRepository.save(course);

        Course course1 = courseRepository.findById(10001L);
        Assert.assertEquals("Updated course", course1.getName());
    }

//    @Test
//    @DirtiesContext
    public void playWithEntityManager() {
        courseRepository.playWithEntityManager();
    }
}
