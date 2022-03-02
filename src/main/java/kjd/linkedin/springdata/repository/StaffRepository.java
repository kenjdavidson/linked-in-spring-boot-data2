package kjd.linkedin.springdata.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import kjd.linkedin.springdata.domain.Staff;

public interface StaffRepository extends PagingAndSortingRepository<Staff, String> {
    List<Staff> findByPersonLastName(String lastName);
    
    @Query(" { person.lastName: ?0 ")
    List<Staff> findByLastName(String lastName);
}
