package kjd.linkedin.springdata.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kjd.linkedin.springdata.domain.Course;

@SpringBootTest
public class CourseRepositoryTest {
    @Autowired
    CourseRepository repo;

    @Test
    void testFindByChairLastName() {
        List<Course> courses = repo.findByChairLastName("Jones");
        assertEquals(2, courses.size());
    }

    @Test
    void testFindByDepartmentChairPersonLastName() {
        List<Course> courses = repo.findByDepartmentChairPersonLastName("Jones");
        assertEquals(2, courses.size());
    }

    @Test
    void testFindByName() {
        Course course = repo.findByName("History 101");
        assertNotNull(course);
    }
}
