package product.example.capstoneproject;

//import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Product_details")
public class Product {

    @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  //  @Column(name = "Product_name")
    private String name;

 //   @Column(name= "Product_description")
    private String description;

    //    @Column(name= "Product_category")
//    private String category;
 //   @Column(name= "Product_price")
    private double price;
    //    @Column(name= "Product_manufacturer")
//    private String manufacturer;
  //  @Column(name= "Product_imageUrl")
    private String imageUrl;

    Product(){
    }

    public Product(String name, String description, double price, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

//    public String getManufacturer() {
//        return manufacturer;
//    }
//
//    public void setManufacturer(String manufacturer) {
//        this.manufacturer = manufacturer;
//    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

//    @Override
//    public String toString() {
//        return "Product{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                // ", category='" + category + '\'' +
//                ", price=" + price +
//                // ", manufacturer='" + manufacturer + '\'' +
//                ", imageUrl='" + imageUrl + '\'' +
//                '}';
//    }
}