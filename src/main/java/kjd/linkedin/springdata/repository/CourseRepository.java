package kjd.linkedin.springdata.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import kjd.linkedin.springdata.domain.Course;

public interface CourseRepository extends MongoRepository<Course, String> {
    
    Course findByName(String name);

    List<Course> findByDepartmentChairPersonLastName(String lastName);
    
    @Query("{ person.firstName : ?0 }")
    List<Course> findByChairLastName(@Param("chair") String lastName);
}
