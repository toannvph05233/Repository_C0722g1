package codegym.repository;

import codegym.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface IStudentRepo extends CrudRepository<Student,Long> {
}
