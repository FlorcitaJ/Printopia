
package tech.nocountry.printopia.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.net.URL;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.nocountry.printopia.persistence.enums.ProductType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Product")
@Entity
public class Product {
    
    @Id
    @SequenceGenerator(
            name = "productIdSequence",
            sequenceName = "productIdSequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "productIdSequence")
    private Integer id;
    @Column(nullable = false,length = 50)
    private String name;
    @Column(length = 500)
    private String description;
    @Column(nullable = false)
    private ProductType type;
    @Column(length = 45)
    private String sku;
    @Column(length = 25)
    private String barCode;
    @Column(columnDefinition = "DOUBLE(4,2)")
    private Double weight;
    private Integer depth;
    private Integer width;
    private Integer height;
    @Column(columnDefinition = "TINYINT")
    private Boolean showInStore;
    @Column(columnDefinition = "TINYINT")
    private Boolean isPromotional;
    @Column(nullable = false, columnDefinition = "DOUBLE(8,2)")
    private Double price;
    @Column(columnDefinition = "DOUBLE(8,2)")
    private Double promotionPrice;
    private Integer stock;
    private URL photo;
    
}

