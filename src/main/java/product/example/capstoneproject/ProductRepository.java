package product.example.capstoneproject;


//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.List;
@CrossOrigin
@Repository
public interface ProductRepository extends MongoRepository<Product , Long> {

//    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE %:productName%")
//    List<Product> searchProductByName(@Param("productName") String productName);
}
