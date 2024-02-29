
package tech.nocountry.printopia.web.controller;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.nocountry.printopia.persistence.entity.Category;
import tech.nocountry.printopia.persistence.entity.Product;
import tech.nocountry.printopia.persistence.enums.ProductType;
import tech.nocountry.printopia.service.ProductService;
/**
 *
 * @author flopy
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private final ProductService productService;
    
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    
    @GetMapping
    public List<Product> getAllProducts(){
        return productService.findAllProducts();
    }
    
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) throws Exception{
        return productService.findProductById(id);
    }
    
    @GetMapping("/search/name")
    public Product getProductByName(@RequestParam(name = "name") String name){
        return productService.findProductByName(name);
    }
    
    @GetMapping("/search/categories")
    public List<Product> getProductByCategory(@RequestParam(name = "categories") Collection<Category> categories){
        return productService.findProductByCategory(categories);
    }
    
    @GetMapping("/search/description")
    public List<Product> getProductByDescriptionContaining(@RequestParam(name = "description") String description){
        return productService.findProductByDescriptionContaining(description);
    }
    
    @GetMapping("/search/type")
    public List<Product> getProductByType(@RequestParam(name = "type") ProductType type){
        return productService.findProductByType(type);
    }
    
    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) throws Exception{
       return productService.save(product);
    }
    
    @PostMapping("/update/{id}")
    public Product update(@PathVariable Integer id, @RequestBody Product product) throws Exception{
        return productService.update(id, product);
    }    
    
    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Integer id) throws Exception{
        productService.deleteProduct(id);
    }
}
