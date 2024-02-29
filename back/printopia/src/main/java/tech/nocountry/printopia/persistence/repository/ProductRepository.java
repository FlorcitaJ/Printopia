
package tech.nocountry.printopia.persistence.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.nocountry.printopia.persistence.entity.Category;
import tech.nocountry.printopia.persistence.entity.Product;
import tech.nocountry.printopia.persistence.enums.ProductType;
/**
 *
 * @author flopy
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    @Query("SELECT p FROM Product p WHERE p.name = :name")
    public Product findProductByName(@Param("name")String name);
    
    @Query("SELECT p FROM Product p WHERE p.categories = :name")
    public List<Product> findProductByCategory(@Param("categories")Collection<Category> categories);
    
    @Query("SELECT p FROM Product p WHERE p.description LIKE %:description%" )
    public List<Product> findProductByDescriptionContaining(@Param("description")String description);
    
    @Query("SELECT p FROM Product p WHERE p.type = :type")
    public List<Product> findProductByType(@Param("type")ProductType type);
}
