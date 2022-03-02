package kjd.linkedin.springdata.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import kjd.linkedin.springdata.domain.Staff;

public interface StaffRepository extends PagingAndSortingRepository<Staff, Long> {
    
}
