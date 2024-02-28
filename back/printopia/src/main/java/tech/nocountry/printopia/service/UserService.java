package tech.nocountry.printopia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import tech.nocountry.printopia.persistence.entity.User;
import tech.nocountry.printopia.persistence.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author Raul
 *
 */
@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll(){
        return this.userRepository.findAll();
    }

    public User getByEmail(String emailUser){
        return this.userRepository.findById(emailUser).orElse(null);
    }

    public boolean exists(String emailUser){
        return this.userRepository.existsById(emailUser);
    }

    public User save(User user){
        return this.userRepository.save(user);
    }
}
