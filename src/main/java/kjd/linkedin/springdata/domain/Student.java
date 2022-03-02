package kjd.linkedin.springdata.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
public class Student {
    @Id
    private String id;

    private Person attendee;

    private boolean fullTime;

    @DBRef
    private List<Course> courses = new ArrayList<>();

    public Student(Person attendee, Boolean fullTime) {
        this.attendee = attendee;
        this.fullTime = fullTime;
    }
}
