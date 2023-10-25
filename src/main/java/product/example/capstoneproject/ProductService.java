package product.example.capstoneproject;




import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product getProductById(Long id)
    {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Product addProduct(Product product)
    {
        return productRepository.save(product);
    }

//    public List<Product> searchByname(String productname){
//        return productRepository.searchProductByName(productname);
//    }

//    public void deleteProductById(Long id)
//    {
//        productRepository.deleteById(id);
//    }

    public void deleteProductById(Long id) {
        Optional<Product> productToDelete = productRepository.findById(id);

        if (productToDelete.isPresent()) {
            productRepository.deleteById(id);
        } else {
            // Empty else block, no special handling required for non-existing products
        }
    }



//    public Product updateProduct(long id, Product updateproduct){
//        Product product = productRepository.findById(id).get();
//
//        product.setName(updateproduct.getName());
//        product.setDescription(updateproduct.getDescription());
//        product.setPrice(updateproduct.getPrice());
//        //   product.setManufacturer(updateproduct.getManufacturer());
//        return productRepository.save(product);
//    }

    public Product updateProduct(long id, Product updateproduct) {
        Optional<Product> existingProductOptional = productRepository.findById(id);

        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setName(updateproduct.getName());
            existingProduct.setDescription(updateproduct.getDescription());
            existingProduct.setPrice(updateproduct.getPrice());
            return productRepository.save(existingProduct);
        } else {
            // Handle the case when the product doesn't exist, for example, by returning null or throwing an exception
            return null; // or throw a custom exception
        }
    }

}

