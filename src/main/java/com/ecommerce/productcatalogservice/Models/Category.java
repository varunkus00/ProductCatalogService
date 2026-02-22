package com.ecommerce.productcatalogservice.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{

    public String name;
    public String description;
    @OneToMany( mappedBy = "category")
    //@JsonIgnore
    public List<Product> products;


}
