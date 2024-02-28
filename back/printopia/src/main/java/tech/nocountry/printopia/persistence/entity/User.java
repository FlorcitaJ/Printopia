
package tech.nocountry.printopia.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.nocountry.printopia.persistence.enums.Role;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Entity
public class User {
    @Id
    @SequenceGenerator(
            name = "userIdSequence",
            sequenceName = "userIdSequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "userIdSequence")
    private String email;
    @Column(nullable = false,length = 50)
    private String name;
    @Column(nullable = false,length = 50)
    private String lastName;
    @Column(nullable = false,length = 70)
    private String hash;
    @Column(nullable = false,length = 32)
    private String salt;
    @Column(nullable = false)
    private Role role;
    @JsonBackReference
    @OneToMany(mappedBy = "user")
    private Set<ConsolidatedSale> consolidatedSales;
}
