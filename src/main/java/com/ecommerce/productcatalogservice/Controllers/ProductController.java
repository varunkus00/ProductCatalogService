package com.ecommerce.productcatalogservice.Controllers;

import com.ecommerce.productcatalogservice.DTOs.CategoryDTO;

import com.ecommerce.productcatalogservice.DTOs.ProductDTO;

import com.ecommerce.productcatalogservice.Models.Category;
import com.ecommerce.productcatalogservice.Models.Product;
import com.ecommerce.productcatalogservice.Service.IProductService;
import com.ecommerce.productcatalogservice.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    IProductService productService;

    @Autowired
    ProductService IService;

    @GetMapping("/product/{id}")
    ProductDTO getProductById(@PathVariable("id") long id) {
        //Product product = productService.getProductById(id);
        Product product = IService.getProductById(id);
        return from(product);
    }

    @GetMapping("/product")
    List<ProductDTO> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for( Product product : products) {
            productDTOs.add(from(product));
        }
        return productDTOs;
    }

    @PostMapping("/product")
    ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        Product product = from(productDTO);
        Product newProduct = IService.createProduct(product);
        return from(newProduct);
    }

    @PutMapping("/product/{id}")
    ProductDTO replaceProduct(@PathVariable Long id,  @RequestBody ProductDTO productDTO) {
        Product product = from(productDTO);
        Product updatedProduct = productService.replaceProduct(id, product);
        return from(updatedProduct);
    }

    @DeleteMapping("/product/{id}")
    Boolean deleteProduct(@PathVariable Long id) {
        Product product = productService.deleteProductById(id);
        return Boolean.TRUE;
    }

    ProductDTO from( Product product ) {
        ProductDTO productDTO = new ProductDTO();
        if( product != null) {
            productDTO.setId(product.getId());
            productDTO.setName( product.getName());
            productDTO.setDescription(product.getDescription());
            productDTO.setUnitPrice(product.getUnitPrice());
            productDTO.setImageURL(product.getImageUrl());
            if( product.getCategory() != null) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setName(product.getCategory().getName());
                categoryDTO.setDescription(product.getCategory().getDescription());
                productDTO.setCategoryDTO(categoryDTO);
            }
        }
        return productDTO;
    }

    Product from( ProductDTO productDto ) {
        Product product = new Product();
        if( productDto != null) {
            product.setId(productDto.getId());
            product.setName( productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setUnitPrice(productDto.getUnitPrice());
            product.setImageUrl(productDto.getImageURL());
            if( productDto.getCategoryDTO() != null) {
                Category category = new Category();
                category.setId(productDto.getCategoryDTO().getId());
                category.setName(productDto.getCategoryDTO().getName());
                category.setDescription(productDto.getCategoryDTO().getDescription());
                product.setCategory(category);
            }
            System.out.println(" ProductId : " + productDto.getId() + " ProductName : " + productDto.getName());
        }
        return product;
    }

}
