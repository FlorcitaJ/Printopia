
package tech.nocountry.printopia.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.nocountry.printopia.persistence.enums.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Entity
public class User {
    @Id
    @Column(length = 50)
    private String email;
    @Column(nullable = false,length = 50)
    private String name;
    @Column(nullable = false,length = 50)
    private String lastName;
    @Column(nullable = false,length = 200)
    private String password;
    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean locked;
    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean disabled;
    @Enumerated(EnumType.STRING)
    private Role role;
}
