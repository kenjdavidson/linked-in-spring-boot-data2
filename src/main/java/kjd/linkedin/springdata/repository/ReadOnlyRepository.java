package kjd.linkedin.springdata.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;

public interface ReadOnlyRepository<T, ID extends Serializable> extends Repository<T, ID> {    
    Optional<T> findById(ID id);
    Iterable<T> findAll();
    Iterable<T> findAllById(Iterable<ID> iterable);
    Iterable<T> findAll(Sort sort);
    Page<T> findAll(Pageable pageable);

    long count();
    boolean existsById(ID id);
}
