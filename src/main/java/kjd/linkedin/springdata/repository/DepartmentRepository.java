package kjd.linkedin.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kjd.linkedin.springdata.domain.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
}
