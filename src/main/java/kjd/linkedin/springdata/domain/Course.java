package kjd.linkedin.springdata.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Entity
@Table(name="courses")
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@ToString
public class Course {
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    @NonNull
    private String name;

    @OneToOne
    private Staff professor;

    @ManyToOne
    @JoinColumn(name="dept_id")
    private Department department;

    /**
     * Should maybe be {@code @ManyToMany} since a course could be a prerequisite for multiple other
     * courses?
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="course_prerequisites",
                joinColumns = { @JoinColumn(name = "prerequisite_id") },
                inverseJoinColumns = { @JoinColumn(name = "course_id") })
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
