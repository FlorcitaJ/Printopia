package tech.nocountry.printopia.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sale_detail")
@Entity
public class SaleDetail {

    @Id
    @SequenceGenerator(
            name = "saleDetailIdSequence",
            sequenceName = "saleDetailIdSequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "saleDetailIdSequence")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="idConsolidatedSale", referencedColumnName = "id",nullable = false)
    private ConsolidatedSale consolidatedSale;

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id")
    @JsonManagedReference
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(columnDefinition = "DOUBLE(8,2)", nullable = false)
    private Double price;

    @Column(columnDefinition = "DOUBLE(8,2)", nullable = false)
    private Double weight;

    @Column(columnDefinition = "TINYINT")
    private Boolean wasPromotional;

}