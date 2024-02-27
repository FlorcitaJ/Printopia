package tech.nocountry.printopia.web.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import tech.nocountry.printopia.persistence.entity.User;
import tech.nocountry.printopia.service.UserService;
import org.springframework.validation.BindingResult;

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
    public ResponseEntity<?> register(@Valid @RequestBody User t, BindingResult bindingResult){

            if(bindingResult.hasErrors()) {
                StringBuilder errorMessage = new StringBuilder("Validation errors: ");
                for (FieldError error : bindingResult.getFieldErrors()) {
                    errorMessage.append(error.getField()).append(" ").append(error.getDefaultMessage()).append("; ");
                }
                return ResponseEntity.badRequest().body(errorMessage.toString());
            }
            if (!this.userService.exists(t.getEmail())) {
                return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.save(t));
            }
            // HTTP CODE: 409
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This email is already registered");
    }
}

