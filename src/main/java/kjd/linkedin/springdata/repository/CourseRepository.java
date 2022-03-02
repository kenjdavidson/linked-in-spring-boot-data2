package kjd.linkedin.springdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kjd.linkedin.springdata.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    
    Course findByName(String name);

    List<Course> findByDepartmentChairPersonLastName(String lastName);
    
    @Query("select c from Course c where c.department.chair.person.lastName=:chair")
    List<Course> findByChairLastName(@Param("chair") String lastName);
}
