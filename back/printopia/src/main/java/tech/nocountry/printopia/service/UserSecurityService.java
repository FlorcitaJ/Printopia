package tech.nocountry.printopia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.nocountry.printopia.persistence.entity.User;
import tech.nocountry.printopia.persistence.repository.UsersRepository;

@Service
public class UserSecurityService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserSecurityService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.usersRepository.findById(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email " + email + " not found."));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().toString())
                .accountLocked(user.getLocked())
                .disabled(user.getDisabled())
                .build();
    }

}
