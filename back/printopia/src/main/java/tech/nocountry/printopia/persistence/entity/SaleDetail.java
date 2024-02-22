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
@Table(name = "Sale_Detail")
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
    @Column(nullable = false)
    @JoinColumn(name="consolidated_sale")
    private Integer idConsolidatedSale;


    @Column(length = 500)
    private LocalDate date;

    @OneToMany
    @Column(nullable = false)
    private Integer idProduct;

    @Column(nullable = false)
    private Integer quantity;

    @Column(columnDefinition = "DOUBLE(8,2)", nullable = false)
    private Double price;

    @Column(columnDefinition = "DOUBLE(8,2)", nullable = false)
    private Double weight;

    @Column(columnDefinition = "TINYINT")
    private Boolean wasPromotional;


}