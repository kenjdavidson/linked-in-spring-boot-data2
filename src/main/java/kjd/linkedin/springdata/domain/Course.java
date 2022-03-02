package kjd.linkedin.springdata.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents the {@code courses} table.
 * <p>
 * {@code courses.dept_id} is a foreign key to {@code departments.id}.
 * <p>
 * 
 * @author kenjdavidson
 */
@Document
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@ToString
public class Course {
    @Id
    private String id;

    private String name;

    @DBRef
    private Staff professor;

    @DBRef
    private Department department;

    /**
     * Should maybe be {@code @ManyToMany} since a course could be a prerequisite for multiple other
     * courses?
     */
    @DBRef
    private Set<Course> prerequisites = new HashSet<>();

    public Course(String name, Staff prof, Department department) {
        this.name = name;
        this.professor = prof;
        this.department = department;
    }

    public Course addPrerequestite(Course course) {
        this.prerequisites.add(course);
        course.setDepartment(department);
        return this;
    }
}
