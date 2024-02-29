package tech.nocountry.printopia.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.nocountry.printopia.persistence.entity.Product;
import tech.nocountry.printopia.persistence.enums.ProductType;
import tech.nocountry.printopia.persistence.repository.ProductRepository;
/**
 *
 * @author flopy
 */
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    
    @Transactional
    public Product save(Product product) throws Exception{
        validate(product);
        return productRepository.save(product);
    }

    public List<Product> findAllProducts(){
        List<Product> products=productRepository.findAll();
        return products;
    }
    
    public Product findProductById(Integer id) throws Exception{
        Optional<Product>            response=productRepository.findById(id);
        Product product=new Product();
        if(response.isPresent()){
            product=response.get();
        }else{
            throw new Exception("The product with that id was not found, please verify that the id is correct");
        }
        return product;
    }
    
    public Product findProductByName(String name){
        return productRepository.findProductByName(name);
    }
    
    
//   public List<Product> findProductByCategory(String categories){
//        return productRepository.findProductByCategory(categories);
//    }
    
    public List<Product> findProductByDescriptionContaining(String description){
        return productRepository.findProductByDescriptionContaining(description);
    }
    
    public List<Product> findProductByType(ProductType type){
        return productRepository.findProductByType(type);
    }
    
    @Transactional
    public Product update(Integer id, Product updateProduct) throws Exception{
        Product product=findProductById(id);
        if(product!=null){
            validate(updateProduct);
            updateEasy(product, updateProduct);
            return productRepository.save(product);
        }else{
            throw new Exception("The product with that id was not found, please verify that the id is correct");
        }
    }
    
    @Transactional
    public void deleteProduct(Integer id) throws Exception{
        Optional<Product>            response=productRepository.findById(id);
        if(response.isPresent()){
            Product product=response.get();
            productRepository.delete(product);
        }else{
            throw new Exception("The product with that id was not found, please verify that the id is correct");
        }
    }
    
    private void validate(Product product) throws Exception{
        
        if(product.getName()==null){
            throw new Exception("Name can't be blank");
        }else if(product.getName().trim().isEmpty()){
            throw new Exception("Name can't be blank");
        }
        if(product.getDescription()==null){
            throw new Exception("Description can't be blank");
        }else if(product.getDescription().trim().isEmpty()){
            throw new Exception("Description can't be blank");
        }
        if(product.getType()==null){
            throw new Exception("You must asign a type");
        }
        if(product.getSku()==null){
            throw new Exception("Sku can't be blank");
        }else if(product.getSku().trim().isEmpty()){
            throw new Exception("Sku can't be blank");
        }
        if(product.getShowInStore()==null){
            throw new Exception("You must specify if it will be visible in store");
        }
        if(product.getPrice()==null){
            throw new Exception("Price must be greater than zero");
        }else if(product.getPrice()<=0){
            throw new Exception("Price must be greater than zero");
        }
        if(product.getPhoto()==null){
            throw new Exception("You must upload a photo");
        }
    }
    
    private Product updateEasy(Product product,Product updatedProduct){
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setType(updatedProduct.getType());
        product.setSku(updatedProduct.getSku());
        product.setBarCode(updatedProduct.getBarCode());
        product.setWeight(updatedProduct.getWeight());
        product.setDepth(updatedProduct.getDepth());
        product.setWidth(updatedProduct.getWidth());
        product.setHeight(updatedProduct.getHeight());
        product.setShowInStore(updatedProduct.getShowInStore());
        product.setIsPromotional(updatedProduct.getIsPromotional());
        product.setPrice(updatedProduct.getPrice());
        product.setPromotionPrice(updatedProduct.getPromotionPrice());
        product.setStock(updatedProduct.getStock());
        product.setPhoto(updatedProduct.getPhoto());
        product.setPhoto(updatedProduct.getPhoto());
        //product.setCategories(updatedProduct.getCategories());
        return product;
    }
}
