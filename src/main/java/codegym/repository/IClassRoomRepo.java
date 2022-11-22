package codegym.repository;

import codegym.model.ClassRoom;
import org.springframework.data.repository.CrudRepository;

public interface IClassRoomRepo extends CrudRepository<ClassRoom,Long> {
}
