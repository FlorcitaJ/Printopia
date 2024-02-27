package tech.nocountry.printopia.web.controller;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.nocountry.printopia.persistence.entity.User;
import tech.nocountry.printopia.service.UserService;

import java.rmi.server.ExportException;
import java.util.List;

import static java.util.Objects.isNull;

/**
 *
 * @author Raul
 *
 */
//@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
    //List all users
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        try {
            return ResponseEntity.ok(this.userService.getAll());
        } catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    //Return info about an user by email
    @GetMapping("/{emailUser}")
    public ResponseEntity<User> getByEmail(@PathVariable("emailUser") String emailUser){
        try{
            User tmp=this.userService.getByEmail(emailUser);
            if(isNull(tmp)){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(tmp);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
    */


    //Validate if email and password are correct
    @GetMapping("/validate")
    public ResponseEntity<?> validateUSer(@RequestBody() User user){
        try{
            User tmp=this.userService.getByEmail(user.getEmail());
            if(isNull(tmp)){
                return ResponseEntity.noContent().build();
            }else{
                int result = user.getPassword().compareTo(tmp.getPassword());
                if(result == 0) {return ResponseEntity.ok(true);} else {return ResponseEntity.status((HttpStatus.UNAUTHORIZED)).body("Unauthorized");}

            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }

    }

    //Register a new user
    @PostMapping("/register")
    @Transactional
    public ResponseEntity<?> register(@RequestBody User t){
        try {
                if (!this.userService.exists(t.getEmail())) {
                    if (t.getEmail() != null) {
                        return ResponseEntity.ok(this.userService.save(t));
                    } else {
                        return ResponseEntity.status(HttpStatus.CONFLICT).body("some data are missing, please check");
                    }
                }
                // HTTP CODE: 409
                return ResponseEntity.status(HttpStatus.CONFLICT).body("This email is already registered");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

