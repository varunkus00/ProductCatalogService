package com.ecommerce.productcatalogservice.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{

    private String name;
    private String description;
    @ManyToOne( cascade = CascadeType.ALL)
    private Category category;
    private Double unitPrice;
    private String imageUrl;
    @Enumerated(EnumType.ORDINAL)
    public ProductStatus status;
}
