package kjd.linkedin.springdata.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents the {@code students} table.
 * <p>
 * Studends have multiple courses (in this instance, but Courses should also have numerous students)
 * which are defined using the {@code enrollment} table by mapping:
 * <p>
 * <ul>
 *  <li>{@code enrollments.student_id} to {@code students.id}</li>
 *  <li>{@code enrollments.course_id} to {@code courses.id}</li>
 * </ul>
 * <p>
 * Any changes made to the Student (delete) should cascade through the {@code enrollments} to
 * the {@code courses}.
 * <p>
 * 
 * @author kenjdavidson
 */
@Entity
@Table(name="students")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Embedded
    private Person attendee;

    @Column(name="full_time")
    private boolean fullTime;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="enrollments",
                joinColumns = { @JoinColumn(name = "student_id") },
                inverseJoinColumns = { @JoinColumn(name = "course_id") })
    private List<Course> courses = new ArrayList<>();

    public Student(Person attendee, Boolean fullTime) {
        this.attendee = attendee;
        this.fullTime = fullTime;
    }
}
