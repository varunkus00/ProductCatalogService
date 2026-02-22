package com.ecommerce.productcatalogservice.Service;

import com.ecommerce.productcatalogservice.DTOs.SortParam;
import com.ecommerce.productcatalogservice.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ISearchService {
    public Page<Product> search(String query, int pageNumber, int pageSize, List<SortParam> sortParams);
}
