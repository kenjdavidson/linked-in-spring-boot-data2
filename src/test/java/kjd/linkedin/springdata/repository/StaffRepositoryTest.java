package kjd.linkedin.springdata.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import kjd.linkedin.springdata.domain.Staff;

@SpringBootTest
public class StaffRepositoryTest {
    @Autowired
    StaffRepository repo;

    @Test
    void testPaging() {
        Page<Staff> staff = repo.findAll(PageRequest.of(0, 2));
        assertEquals(2, staff.getSize());
    }

    @Test
    void testPagingShouldFail() {
        Page<Staff> staff = repo.findAll(PageRequest.of(0, 4));
        assertNotEquals(2, staff.getSize());
    }
}
