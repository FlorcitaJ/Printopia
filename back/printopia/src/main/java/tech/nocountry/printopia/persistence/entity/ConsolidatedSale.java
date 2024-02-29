package tech.nocountry.printopia.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "consolidated_sale")
@Entity
public class ConsolidatedSale {

    @Id
    @SequenceGenerator(
            name = "consolidatedSaleIdSequence",
            sequenceName = "consolidatedSaleIdSequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "consolidatedSaleIdSequence")
    private Integer id;

    @Column(columnDefinition = "DATE")
    private LocalDate saleDate;

    @Column(columnDefinition = "DOUBLE(8,2)", nullable = false)
    private Double shippingCost;

    @Column(columnDefinition = "DOUBLE(8,2)", nullable = false)
    private Double totalCost;


    @ManyToOne
    @JoinColumn(name = "userEmail", referencedColumnName = "email", nullable = false)

    //@Column(nullable = false,length = 50)
    private User user;

// TODO: la relacion parece erronea , hay que revisarla
//    @OneToMany(mappedBy = "consolidatedSale")
//        private Set<SaleDetail> saleDetails;
// Failed to evaluate Jackson deserialization for type [[simple type, class tech.nocountry.printopia.persistence.entity.User]]:
// com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot handle managed/back reference 'defaultReference':
// no back reference property found from type `tech.nocountry.printopia.persistence.entity.Product`


}