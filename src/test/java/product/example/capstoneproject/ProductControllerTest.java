package product.example.capstoneproject;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        // Create a list of sample products
        List<Product> sampleProducts = new ArrayList<>();
        sampleProducts.add(new Product());
        sampleProducts.add(new Product());

        // Mock the ProductService to return the sample products when getAllProduct is called
        when(productService.getAllProduct()).thenReturn(sampleProducts);

        // Call the controller method to get all products
        ResponseEntity<List<Product>> response = productController.getAllProducts();

        // Verify that the response status code is HttpStatus.OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Verify that the response body matches the sample products
        assertEquals(sampleProducts, response.getBody());
    }


    @Test
    public void testGetProductByIdWhenProductDoesNotExist() {
        Long productId = 2L; // A product that doesn't exist

        // Mock the ProductService to return null when getProductById is called
        when(productService.getProductById(productId)).thenReturn(null);

        // Call the controller method to get a non-existent product by ID
        ResponseEntity<Product> response = productController.getProductById(productId);

        // Verify that the response status code is HttpStatus.NOT_FOUND (404)
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddProductWithValidData() {
        // Create a new product
        Product productToAdd = new Product();
        productToAdd.setName("New Product");

        // Mock the ProductService to return the saved product
        when(productService.addProduct(productToAdd)).thenReturn(productToAdd);

        // Call the controller method to add a product
        ResponseEntity<Product> response = productController.addProduct(productToAdd);

        // Verify that the response status code is HttpStatus.CREATED (201)
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        // Verify that the added product matches the input
        assertEquals(productToAdd, response.getBody());
    }

    @Test
    public void testAddProductWithMissingData() {
        // Create a new product with missing data
        Product productToAdd = new Product(); // No name set

        // Call the controller method to add a product
        ResponseEntity<Product> response = productController.addProduct(productToAdd);

        // Verify that the response status code is HttpStatus.BAD_REQUEST (400) due to missing data
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testDeleteProductWhenProductExists() {
        Long productId = 1L;

        // Call the controller method to delete a product
        ResponseEntity<Product> response = productController.deleteProduct(productId);

        // Verify that the response status code is HttpStatus.NO_CONTENT (204)
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        // Verify that the ProductService's deleteProductById method was called
        verify(productService, times(1)).deleteProductById(productId);
    }

    @Test
    public void testGetProductByIdWhenProductExists() {
        // Create a sample product with known attributes
        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setName("Sample Product");
        expectedProduct.setDescription("A description");
        expectedProduct.setPrice(99.99);

        // Mock the ProductService to return the expected product when getProductById is called
        when(productService.getProductById(expectedProduct.getId())).thenReturn(expectedProduct);

        // Call the controller method to get a product by ID
        ResponseEntity<Product> response = productController.getProductById(expectedProduct.getId());

        // Verify that the response status code is HttpStatus.OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Verify that the response body matches the expected product
        assertEquals(expectedProduct, response.getBody());
    }



    @Test
    public void testUpdateProductWithValidData() {
        // Create an existing product with known attributes
        Product existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setName("Existing Product");
        existingProduct.setDescription("An existing product");
        existingProduct.setPrice(69.99);

        // Create an updated product with changes
        Product updatedProduct = new Product();
        updatedProduct.setName("Updated Product");
        updatedProduct.setDescription("Updated description");
        updatedProduct.setPrice(79.99);

        // Mock the ProductService to return the existing product and update it
        when(productService.getProductById(existingProduct.getId())).thenReturn(existingProduct);
        when(productService.updateProduct(existingProduct.getId(), updatedProduct)).thenReturn(updatedProduct);

        // Call the controller method to update the product
        ResponseEntity<Product> response = productController.updateProduct(existingProduct.getId(), updatedProduct);

        // Verify that the response status code is HttpStatus.OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Verify that the response body matches the updated product
        assertEquals(updatedProduct, response.getBody());
    }

    @Test
    public void testUpdateProductWithInvalidId() {
        // Create a non-existent product (an empty optional)
        Long productId = 2L;
        when(productService.getProductById(productId)).thenReturn(null);

        // Create an updated product with changes
        Product updatedProduct = new Product();
        updatedProduct.setName("Updated Product");
        updatedProduct.setDescription("Updated description");
        updatedProduct.setPrice(79.99);

        // Call the controller method to update a non-existent product
        ResponseEntity<Product> response = productController.updateProduct(productId, updatedProduct);

        // Verify that the response status code is HttpStatus.NOT_FOUND (404)
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddProductWithInvalidData() {
        // Create a new product with missing data (no name)
        Product productToAdd = new Product();

        // Call the controller method to add a product
        ResponseEntity<Product> response = productController.addProduct(productToAdd);

        // Verify that the response status code is HttpStatus.BAD_REQUEST (400) due to missing data
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


}
