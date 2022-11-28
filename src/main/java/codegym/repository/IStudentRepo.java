package codegym.repository;

import codegym.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface IStudentRepo extends PagingAndSortingRepository<Student,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM Student where name =:name")
    Student findByName(@Param("name") String name);
}
