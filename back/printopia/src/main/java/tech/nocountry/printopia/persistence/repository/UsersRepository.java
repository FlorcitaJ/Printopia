package tech.nocountry.printopia.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import tech.nocountry.printopia.persistence.entity.User;

public interface UsersRepository extends CrudRepository<User, String> {


}
