package com.ecommerce.productcatalogservice.Service;

import com.ecommerce.productcatalogservice.DTOs.FakeStoreApiDto;
import com.ecommerce.productcatalogservice.Models.Category;
import com.ecommerce.productcatalogservice.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService{

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Override
    public Product getProductById(long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreApiDto fakeStoreApiDto = restTemplate.getForObject("https://fakestoreapi.com/products/{id}", FakeStoreApiDto.class,id);

        return from(fakeStoreApiDto);
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreApiDto[] fakeStoreApiDto = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreApiDto[].class);
        List<FakeStoreApiDto> fakeStoreApiDtoList = Arrays.asList(fakeStoreApiDto);

        return allProducts(fakeStoreApiDtoList);
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreApiDto fakeStoreApiDto = from(product);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreApiDto> fakeStoreApiDtoResponseEntity = restTemplate.postForEntity("https://fakestoreapi.com/products", fakeStoreApiDto, FakeStoreApiDto.class, product.getId());
        if(fakeStoreApiDtoResponseEntity.hasBody() ) {
            return from(fakeStoreApiDtoResponseEntity.getBody());
        }
        return null;
    }

    @Override
    public Product replaceProduct(Long ID, Product product) {
        FakeStoreApiDto fakeStoreApiDto = from(product);

        ResponseEntity<FakeStoreApiDto> fakeStoreApiDtoResponseEntity = putForEntity("https://fakestoreapi.com/products/{id}", fakeStoreApiDto, FakeStoreApiDto.class, ID);

        if( fakeStoreApiDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200)) && fakeStoreApiDtoResponseEntity.hasBody()){
            return from(fakeStoreApiDtoResponseEntity.getBody());
        }

        return null;
    }

    @Override
    public Product deleteProductById(Long ID) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete("https://fakestoreapi.com/products/{id}", ID);
        return null;
    }

    public <T> ResponseEntity<T> putForEntity(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }

    Product from(FakeStoreApiDto fakeStoreApiDto) {
        Product product = new Product();
        product.setId(fakeStoreApiDto.getId());
        product.setName(fakeStoreApiDto.getTitle());
        product.setDescription(fakeStoreApiDto.getDescription());
        product.setImageUrl(fakeStoreApiDto.getImage());
        Category category = new Category();
        if( fakeStoreApiDto.getCategory() != null ) {
            category.setName(fakeStoreApiDto.getCategory());
        }
        product.setCategory(category);
        product.setUnitPrice(fakeStoreApiDto.getPrice());

        return  product;
    }

    FakeStoreApiDto from(Product product) {
        FakeStoreApiDto fakeStoreApiDto = new FakeStoreApiDto();
        fakeStoreApiDto.setId(product.getId());
        fakeStoreApiDto.setTitle(product.getName());
        fakeStoreApiDto.setDescription(product.getDescription());
        fakeStoreApiDto.setImage(product.getImageUrl());

        if( product.getCategory() != null ) {
            fakeStoreApiDto.setCategory(product.getCategory().getName());
        }

        fakeStoreApiDto.setPrice(product.getUnitPrice());
        return fakeStoreApiDto;
    }

    List<Product> allProducts(List<FakeStoreApiDto> fakeStoreApiDtoList) {
        List<Product> products = new ArrayList<>();
        for(FakeStoreApiDto fakeStoreApiDto : fakeStoreApiDtoList) {
            Product product = new Product();
            product.setId(fakeStoreApiDto.getId());
            product.setName(fakeStoreApiDto.getTitle());
            product.setDescription(fakeStoreApiDto.getDescription());
            product.setImageUrl(fakeStoreApiDto.getImage());
            Category category = new Category();
            if( fakeStoreApiDto.getCategory() != null ) {
                category.setName(fakeStoreApiDto.getCategory());
            }
            product.setCategory(category);
            product.setUnitPrice(fakeStoreApiDto.getPrice());

            products.add(product);
        }
        return products;
    }
}
