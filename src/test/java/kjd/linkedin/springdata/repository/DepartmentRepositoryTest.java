package kjd.linkedin.springdata.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DepartmentRepositoryTest {
    @Autowired
    DepartmentRepository repo;

    @Test
    public void testMethods() {
        // Pretty much just testing the individual methods
    }
}
