package kjd.linkedin.springdata.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kjd.linkedin.springdata.domain.Student;

public interface StudentRepository extends CrudRepository<Student, String> {
    // Simple query methods
    List<Student> findByFullTime(boolean fullTime);
    List<Student> findByAttendeeYearOfBirth(Integer yearOfBirth);
    List<Student> findByAttendeeLastName(String lastName);

    List<Student> findByFullTimeOrAttendeeYearOfBirthLessThan(boolean fulltime, int yearOfBirth);
    List<Student> findByFullTimeOrAttendeeYearOfBirthGreaterThan(boolean fulltime, int yearOfBirth);
    List<Student> findByAttendeeLastNameIgnoreCase(String lastName);
    List<Student> findByAttendeeLastNameLike(String lastName);

    // Now some aggregation(ish)
    Student findFirstByOrderByAttendeeLastNameAsc();
    Student findFirstByOrderByAttendeeYearOfBirthDesc();
    List<Student> findTop3ByOrderByAttendeeYearOfBirthDesc();  
}
