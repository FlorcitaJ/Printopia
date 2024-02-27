
package tech.nocountry.printopia.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "cannot be null or blank")
    @Size(min = 5, max = 50, message = "must have between 5 and 50 characters")
    @Email(message="invalid")
    private String email;
    @Column(nullable = false,length = 50)
    @NotNull
    private String name;
    @Column(nullable = false,length = 50)
    @NotNull
    private String lastName;
    @Column(nullable = false,length = 200)
    @NotNull
    private String password;
    @Column(nullable = false, columnDefinition = "TINYINT")
    @NotNull
    private Boolean locked;
    @NotNull
    @Column(nullable = false, columnDefinition = "TINYINT")
    @NotNull
    private Boolean disabled;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;
}
