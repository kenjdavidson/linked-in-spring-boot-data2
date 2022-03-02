package kjd.linkedin.springdata.domain;

import java.util.ArrayList;
import java.util.List;

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
 * Represents the {@code departments} table.
 * <p>
 * {@code courses} is mapped using the {@code Course#getDepartment()} configuration.  Which is setup
 * as {@code dept_id}.
 * <p>
 * 
 * @author kenjdavidson
 */
@Document
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@ToString
public class Department {
    @Id
    private String id;

    @NonNull
    private String name;

    @DBRef
    private List<Course> courses = new ArrayList<>();

    @DBRef
    private Staff chair;

    public Department(String name, Staff chair) {
        this.name = name;
        this.chair = chair;
    }
}
