package com.ecommerce.productcatalogservice.Service;

import com.ecommerce.productcatalogservice.Models.Product;
import com.ecommerce.productcatalogservice.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepo  productRepo;

    @Override
    public Product getProductById(long id) {
        Optional<Product> productOp = productRepo.findById(id);
        if( productOp.isPresent() ) {
            return productOp.get();
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {

        List<Product> allProducts = productRepo.findAll();
        return allProducts;
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Product> productOp = productRepo.findById(product.getId());
        if( productOp.isPresent() ) {
            return productOp.get();
        }
        return productRepo.save(product);
    }

    @Override
    public Product replaceProduct(Long ID, Product product) {
        return null;
    }

    @Override
    public Product deleteProductById(Long ID) {
        return null;
    }
}
