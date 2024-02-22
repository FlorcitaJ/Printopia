package tech.nocountry.printopia.persistence.entity;

import jakarta.persistence.*;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Consolidated_Sale")
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

    @OneToMany(mappedBy = "Sale_Detail")
    @Column(nullable = false)
    private Integer idShippingMethod;

    @Column(columnDefinition = "DOUBLE(8,2)", nullable = false)
    private Double shippingCost;

    @Column(columnDefinition = "DOUBLE(8,2)", nullable = false)
    private Double totalCost;

    @Column(nullable = false,length = 50)
    private String userEmail;


}