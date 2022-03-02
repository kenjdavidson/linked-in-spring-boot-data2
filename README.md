# Linked In - Spring Data 2

## Chapter 2 - Understanding JPA Object Relational Mapping

### Logical model vs Physical model

### History of Java Persistence API

The first populate ORMs:

- Hibernate
- Toplink
- IBatis

JPA is a specification that frameworks provide the implementation.

- `EntityManager` performs creating, reading, updating and deleting

> This tutorial concentrates on Annotations (not XML)

### Map Tables to Entities

### Map Multiple Tables to Java Classes

Mapped using:

- `@OneToMany` when this class has many of the other
- `@ManyToOne` when this class has multiple but only one of the other
- `@OneToOne`

The example uses an `entrollemnts` table containing:

- `student_id`
- `course_id` 

but does not include extra fields important to an enrollment:

- `enrollment_date`
- `final_grade`
- etc

Unsure of JPA (Hibernate) methods of doing this, or if there is another class `StudentScores` although it seems more related to the `Enrollment` idea.

### Query Language

Interact with entities using `JPQL`.

- queries are portable
- close to SQL but references entities and attributes instead of columns

A comparison of the two:

#### SQL

Get a student with their courses and department, then we would need to map the fields and objects manually:

```
select s.id, s.name, s.full_time, s.yob, c.id, c.name, d.name
from students s
join enrollment e on e.student_id = s.id
join course c on c.id = e.course_id
join department d on d.id = c.dept_id
where s.id = 1;
```

#### JPQL

To do this with the `EntityMapper`:

```
entityManager.createQuery("select s from students s where s.name="'jane'", Student.class)
    .getSingleResult();
```

> Some weird comment about not being able to run `Student#toString()` if the `Course` list hasn't been fetched is kind of crazy.  I get the `could not initialize proxy` error, but why wouldn't it just handle the fact that there are no courses yet?

## Chapter 3 - Introduction to Spring Data JPA

### JPA without Spring Example

Creating:

```
@PersistenceContext
EntityManager entityManager;

Student create(String name, Boolean fulltime, Integer yob) {
    Student student = new Student(name, fulltime, yob);

    entityManager.getTransaction().begin();    
    entityManager.getTransaction().persist(student);
    entityManager.getTransaction().commit();
    entityManager.getTransaction().close();
    
    return student;
}
```

> Forgetting to close or commit are comming mistakes

### Spring Data Repositories (Interface)

Better wrapping of this code, abstracting away the implementations:

```
public interface StudentRepository extends Repository<Student, Long> {
    // ... methods following naming structures
}

public interface StudentRepository extends CrudRepository<Student, Long> {
    // ... methods following naming structures
}
```

Which now provide the `StudentRepistory` with the following standard methods:

- `save`
- `saveAll`
- `delete`
- `deleteAll`
- `update`
- `findBy...`

### CrudRepository Interface

See example(s):

- `StudentRepository`
- `StudentRepositoryTest`

### JPA Respoitory

`JpaRepository` extends from `CrudRepository` providing some added:

- `flush`
- `saveAndFlush`
- `deleteInBatch`
- `deleteAllInBatch`

which are a little more efficient.  For example if:

- You need to flush directly, no need to inject `EntityManager`
- Helps extend and use differnet repositories (mongo, solr, gemfire, etc)

See example(s):

- `DepartmentRepository`
- `DepartmentRepositoryTest`


## Quering with Spring 

When needing to write/lookup details using more specific requirements.

Rules:

- Return type
- `findBy`
- Entity attribute name (camel case)
- Optionally, chain sub attribute names
- Add any query parameters

> Spring data will fast fail during startup if any of these methods are not available

Check out **https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords** of the Spring Data repository for all the list of keywords.

## @Query Methods

Some of reasons for using `@Query`:

- Clearer method name

> `findByChairLastName` is better than `findByDepartmentChairMemberLastName`

- The query is too complex for property expressions

> If a join needs to be made or returns a non JPA entity (report)

- Times when going to a native query is required

> Setting `nativeQuery=true` within the `@Query` by using the new command with the supplied Class
> `select new kjd.linkedin.springdata.dto.CourseView (c.name, ...) from Course c where c.id=:id`

## Query By Example

- User friendly alternative to SQL
- Lookup objects similar to other objects
- Use it if frequently refactored code

using the `extends QueryByExampleExecutor<T>`.

### How to Create an Example

- First by using the `Example.of<T>(T probe)`

```
// Create an example to lookup by name
departmentRepository.findOne(Example.of(new Department("Humanities", null)));

// Create an example to lookup chair name
departmentRepository.findOne(Example.of(new Department(null, new Staff(new Person("john", null)))));
```

Using the builder pattern a number of queries can be added to the Example

```
// Lookup a department whose
// name ends with science ignoring case
departmentRepository.findOne(Example.of(new Department("sciences", null), 
    ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.ENDING))));
```