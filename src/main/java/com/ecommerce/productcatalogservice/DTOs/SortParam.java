package com.ecommerce.productcatalogservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortParam {
    private String sortCriteria;
    private SortType sortType;
}
