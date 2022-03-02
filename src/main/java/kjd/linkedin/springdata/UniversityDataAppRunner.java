package kjd.linkedin.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import kjd.linkedin.springdata.domain.Course;
import kjd.linkedin.springdata.domain.Department;
import kjd.linkedin.springdata.domain.Person;
import kjd.linkedin.springdata.domain.Staff;
import kjd.linkedin.springdata.domain.Student;
import kjd.linkedin.springdata.repository.CourseRepository;
import kjd.linkedin.springdata.repository.DepartmentRepository;
import kjd.linkedin.springdata.repository.StaffRepository;
import kjd.linkedin.springdata.repository.StudentRepository;

@Component
public class UniversityDataAppRunner implements ApplicationRunner {
    @Autowired DepartmentRepository departmentRepository;
    @Autowired CourseRepository courseRepository;
    @Autowired StaffRepository staffRepository;
    @Autowired StudentRepository studentRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Students
        studentRepository.save(new Student(new Person("jane", "doe", 1982), true));
        studentRepository.save(new Student(new Person("john", "doe", 1983), true));
        studentRepository.save(new Student(new Person("mike", "smith", 1980), true));
        studentRepository.save(new Student(new Person("mary", "smith", 1977), true));

        // Staff
        Staff deanJones = staffRepository.save(new Staff(new Person("John", "Jones", 1962)));
        Staff deanMartin = staffRepository.save(new Staff(new Person("Matt", "Martin", 1962)));
        Staff profBrown = staffRepository.save(new Staff(new Person("James", "Brown", 1962)));
        Staff profMiller = staffRepository.save(new Staff(new Person("Marty", "Miller", 1962)));

        // Departments
        Department naturalScience = departmentRepository.save(new Department("Natural Science", deanJones));
        Department socialScience = departmentRepository.save(new Department("Social Science", deanMartin));

        // Courses
        courseRepository.save(new Course("Chemistry", profBrown, naturalScience));
        courseRepository.save(new Course("Physics", profMiller, naturalScience));
        Course history101 = courseRepository.save(new Course("History 101", deanMartin, socialScience));
        Course history102 = courseRepository.save(new Course("History 102", deanMartin, socialScience));
        courseRepository.save(history102.addPrerequestite(history101));
    }

    
}
