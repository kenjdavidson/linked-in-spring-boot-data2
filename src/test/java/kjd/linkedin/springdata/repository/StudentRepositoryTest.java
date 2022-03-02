package kjd.linkedin.springdata.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kjd.linkedin.springdata.domain.Person;
import kjd.linkedin.springdata.domain.Student;

@SpringBootTest
public class StudentRepositoryTest {
    @Autowired
    StudentRepository repo;

    @Test 
    public void saveStudent() {
        repo.save(student("Ken", "Davidson", 1980, true));
        repo.save(student("Sue", "Davidson", 1983, false));

        assertEquals(6, repo.count());
    }

    @Test void deleteStudent() {
        Student student = student("Ken", "Davidson", 1980, true);

        repo.save(student);
        repo.delete(student);

        assertEquals(4, repo.count());
    }

    Student student(String firstName, String lastName, Integer yob, Boolean fulltime) {
        return new Student(new Person(firstName, lastName, yob), fulltime);        
    }
}
