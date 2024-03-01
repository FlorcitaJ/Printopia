
package tech.nocountry.printopia.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.net.URL;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.nocountry.printopia.persistence.enums.ProductType;
/**
 *
 * @author flopy
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
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
    @Column(nullable = false, length = 50)
    private String name;
    @Column(length = 500)
    private String description;
    @Column(nullable = false,length = 10)
    @Enumerated(EnumType.STRING)
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

    // @JsonBackReference
    @ManyToOne
      @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category category;

}

