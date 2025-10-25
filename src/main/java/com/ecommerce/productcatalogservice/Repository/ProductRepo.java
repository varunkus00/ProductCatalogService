package com.ecommerce.productcatalogservice.Repository;

import com.ecommerce.productcatalogservice.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long aLong);

    List<Product> findAll();

    Product save(Product product);

}
