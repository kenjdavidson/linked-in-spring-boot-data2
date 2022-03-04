package kjd.linkedin.springdata.repository;

import kjd.linkedin.springdata.domain.Course;

public interface CourseQueryRepository extends ReadOnlyRepository<Course, Long> {
    // Add all read only queries
}
