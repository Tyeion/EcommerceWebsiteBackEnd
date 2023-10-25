package product.example.capstoneproject;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

public class ProductServiceTest {
    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetProductById() {
        Long productId = 1L;
        Product expectedProduct = new Product();
        expectedProduct.setId(productId);
        when(productRepository.findById(productId)).thenReturn(Optional.of(expectedProduct));

        Product actualProduct = productService.getProductById(productId);

        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    public void testGetAllProduct() {
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product());
        expectedProducts.add(new Product());
        when(productRepository.findAll()).thenReturn(expectedProducts);
        List<Product> actualProducts = productService.getAllProduct();
        assertEquals(expectedProducts.size(), actualProducts.size());
    }
    @Test
    public void testAddProduct() {
        Product productToAdd = new Product();
        when(productRepository.save(productToAdd)).thenReturn(productToAdd);
        Product addedProduct = productService.addProduct(productToAdd);
        assertEquals(productToAdd, addedProduct);
    }
    @Test
    public void testDeleteProductById() {
        Long productId = 1L;

        // Mock the repository behavior to return a value when deleteById is called
        when(productRepository.findById(productId)).thenReturn(Optional.of(new Product())); // Mock the product retrieval

        productService.deleteProductById(productId);

        // Verify that the repository's deleteById method was called
        verify(productRepository, times(1)).deleteById(productId);
    }



    @Test
    public void testUpdateProductWhenProductDoesNotExist() {
        Long productId = 1L;
        // Mock the behavior of the repository to return an empty Optional (product does not exist)
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Create an updated product
        Product updatedProduct = new Product();
        updatedProduct.setId(productId);
        updatedProduct.setName("Updated Name");

        // Perform the update
        Product result = productService.updateProduct(productId, updatedProduct);

        // Verify that the result is null or an appropriate response
        assertNull(result);
    }

    @Test
    public void testAddProductWithValidData() {
        // Create a new product
        Product productToAdd = new Product();
        productToAdd.setName("New Product");

        // Mock the behavior of the repository to return the saved product
        when(productRepository.save(productToAdd)).thenReturn(productToAdd);

        // Perform the addition
        Product addedProduct = productService.addProduct(productToAdd);

        // Verify that the added product matches the input
        assertNotNull(addedProduct);
        assertEquals("New Product", addedProduct.getName());
    }

    @Test
    public void testAddProductWithMissingData() {
        // Create a new product with missing data
        Product productToAdd = new Product(); // No name set

        // Perform the addition
        Product addedProduct = productService.addProduct(productToAdd);

        // Verify that the added product is null or an appropriate response
        assertNull(addedProduct);
    }

    @Test
    public void testDeleteProductWhenProductExists() {
        Long productId = 1L;

        // Mock the repository behavior to return a value when deleteById is called
        when(productRepository.findById(productId)).thenReturn(Optional.of(new Product())); // Mock the product retrieval

        // Perform the deletion
        productService.deleteProductById(productId);

        // Verify that the repository's deleteById method was called
        verify(productRepository, times(1)).deleteById(productId);
    }


    @Test
    public void testDeleteProductWhenProductDoesNotExist() {
        Long productId = 2L; // A product that doesn't exist

        // Perform the deletion
        productService.deleteProductById(productId);

        // Verify that the repository's deleteById method was not called
        verify(productRepository, never()).deleteById(productId);
    }


}
