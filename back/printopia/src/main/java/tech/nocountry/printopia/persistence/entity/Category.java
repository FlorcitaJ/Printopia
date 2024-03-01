/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tech.nocountry.printopia.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Cris
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Category")
@Entity
public class Category {

    @Id
    @SequenceGenerator(
            name = "categoryIdSequence",
            sequenceName = "categoryIdSequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "categoryIdSequence")
    private Integer id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, length = 500)
    private String description;
//    @ManyToMany
//    @JoinTable(name = "products_categories",
//        joinColumns = @JoinColumn(name = "category_id"),
//        inverseJoinColumns = @JoinColumn(name = "product_id"))
    
    //@JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Set<Product> products;

}
