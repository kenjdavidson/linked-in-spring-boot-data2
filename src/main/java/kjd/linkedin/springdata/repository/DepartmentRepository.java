package kjd.linkedin.springdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import kjd.linkedin.springdata.domain.Department;

public interface DepartmentRepository extends MongoRepository<Department, String> {
    
}
