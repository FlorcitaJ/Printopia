package tech.nocountry.printopia.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(customizeRequests -> {
                            customizeRequests
                                    .requestMatchers(HttpMethod.GET,"/api/categories/**").hasAnyRole("ADMIN", "USER")
                                    .requestMatchers(HttpMethod.POST,"/api/categories/**").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.GET,"/api/consolidatedsales/**").hasAnyRole("ADMIN", "USER")
                                    .requestMatchers(HttpMethod.POST,"/api/consolidatedsales/**").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.GET,"/api/saledetails/**").hasAnyRole("ADMIN", "USER")
                                    .requestMatchers(HttpMethod.POST,"/api/saledetails/**").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.GET,"/api/products/**").hasAnyRole("ADMIN", "USER")
                                    .requestMatchers(HttpMethod.POST,"/api/products/**").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.GET,"/api/users/validate").anonymous()
                                    .requestMatchers(HttpMethod.POST,"/api/users/register").anonymous()
                                    .requestMatchers(HttpMethod.GET,"/api/users/{emailUser}").hasAnyRole("ADMIN", "USER")
                                    .requestMatchers(HttpMethod.GET,"/api/users").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.PATCH).hasRole("ADMIN")
                                    //.requestMatchers(HttpMethod.PUT).denyAll()
                                    .anyRequest()
                                    .authenticated();
                        }
                )
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());


        return http.build();
    }

//    @Bean
//    public UserDetailsService emoryUsers(){
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//
//        UserDetails customer = User.builder()
//                .username("customer")
//                .password(passwordEncoder().encode("customer123"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, customer);
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
