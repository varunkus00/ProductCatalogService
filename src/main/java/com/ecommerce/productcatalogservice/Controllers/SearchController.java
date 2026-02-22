package com.ecommerce.productcatalogservice.Controllers;

import com.ecommerce.productcatalogservice.DTOs.CategoryDTO;
import com.ecommerce.productcatalogservice.DTOs.ProductDTO;
import com.ecommerce.productcatalogservice.DTOs.SearchRequestDto;
import com.ecommerce.productcatalogservice.Models.Product;
import com.ecommerce.productcatalogservice.Service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/search")
@RestController
public class SearchController {

    @Autowired
    public ISearchService searchService;

    @PostMapping
    public Page<ProductDTO>  searchProduct(@RequestBody SearchRequestDto requestDto) {

        Page<Product> productPage = searchService.search(requestDto.getQuery(),
                requestDto.getPage(),
                requestDto.getPageSize(),
                requestDto.getSortParamList()
        );

        return productPage.map(product -> {
            return from(product);
        });
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
}
