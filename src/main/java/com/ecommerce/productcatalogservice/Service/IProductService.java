package com.ecommerce.productcatalogservice.Service;

import com.ecommerce.productcatalogservice.Models.Product;

import java.util.List;

public interface IProductService {

    public Product getProductById(long id);
    public List<Product> getAllProducts();
    public Product createProduct(Product product);
    public Product replaceProduct(Long ID, Product product);
    public Product deleteProductById(Long ID);
}
