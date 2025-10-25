package com.ecommerce.productcatalogservice.Models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {
    @Id
    public long id;
    public Date creationDate;
    public Date lastUpdateDate;

}
