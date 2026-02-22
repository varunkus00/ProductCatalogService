package com.ecommerce.productcatalogservice.Controllers;

import com.ecommerce.productcatalogservice.DTOs.ProductDTO;
import com.ecommerce.productcatalogservice.Models.Product;
import com.ecommerce.productcatalogservice.Service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private ProductController productController;

    //@MockBean
    //private ProductService productService;

    /*@Test
    public void TestGetProductById_WithValidId_RunSuccessfully() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Iphone 16 Pro");
        product.setUnitPrice(120000.0);
        when( productService.getProductById(product.getId())).thenReturn(product);

        ProductDTO responseEntity = productController.getProductById(product.getId());
        System.out.println(" Test TestGetProductById_WithValidId_RunSuccessfully Intermediate Step");
        //Assert
        assertNotNull(responseEntity);
        assertEquals(product.getId(), responseEntity.getId());
        verify(productService, times(1)).getProductById(product.getId());

        System.out.println(" Test TestGetProductById_WithValidId_RunSuccessfully Successful");
    }

    @Test
    public void TestGetProductById_WithInValidId_RunSuccessfully() {
        Product product = new Product();
        product.setId(-5);
        product.setName("Iphone 16 Pro");
        product.setUnitPrice(120000.0);
        when( productService.getProductById(product.getId())).thenReturn(product);

        ProductDTO responseEntity = productController.getProductById(product.getId());
        //System.out.println(" Test TestGetProductById_WithInValidId_RunSuccessfully Intermediate Step");
        //Assert
        assertNotNull(responseEntity);
        assertEquals(product.getId(), responseEntity.getId());
        verify(productService, times(1)).getProductById(product.getId());

        System.out.println(" Test TestGetProductById_WithInValidId_RunSuccessfully Successful");
    }*/

}
