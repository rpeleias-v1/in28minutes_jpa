package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CourseSpringDataRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseSpringDataRepository repository;

    @Test
    public void findById_CoursePresent() {
        Optional<Course> courseOptional = repository.findById(10001L);
        assertTrue(courseOptional.isPresent());
    }

    @Test
    public void findById_CourseNotPresent() {
        Optional<Course> courseOptional = repository.findById(20002L);
        assertFalse(courseOptional.isPresent());
    }

    @Test
    public void playigAroundWithSpringDataRepository() {
        logger.info("Courses -> {}", repository.findAll());
        logger.info("Count -> {}", repository.count());
    }

    @Test
    public void sort() {
        Sort sortByName = new Sort(Sort.Direction.ASC, "name");
        logger.info("Sorted By name -> {}", repository.findAll(sortByName));
    }

    @Test
    public void pagination() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Course> firstPage = repository.findAll(pageRequest);
        logger.info("First page -> {}", firstPage.getContent());

        Pageable secondPageable = firstPage.nextPageable();
        Page<Course> secondPage = repository.findAll(secondPageable);
        logger.info("Second page -> {}", secondPage.getContent());
    }

    @Test
    public void findByName() {
        logger.info("find By name -> {}", repository.findByName("JPA in 50 Steps"));
    }

    @Test
    public void findByUsingStudentsName() {
        logger.info("find ussing students name -> {}", repository.findByName("Ranga"));
    }
}
