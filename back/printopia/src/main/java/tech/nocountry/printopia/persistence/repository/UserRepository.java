package tech.nocountry.printopia.persistence.repository;

import org.springframework.data.repository.ListCrudRepository;
import tech.nocountry.printopia.persistence.entity.User;

/**
 *
 * @author Raul
 *
 */
public interface UserRepository extends ListCrudRepository<User, String> {

}
