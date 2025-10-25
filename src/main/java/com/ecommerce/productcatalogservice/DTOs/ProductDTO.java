package com.ecommerce.productcatalogservice.DTOs;

import com.ecommerce.productcatalogservice.Models.Category;
import com.ecommerce.productcatalogservice.Models.ProductStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    private long id;
    private String name;
    private String description;

    private CategoryDTO categoryDTO ;
    private Double unitPrice;
    private String imageURL;

}
