package com.ecommerce.productcatalogservice.Service;

import com.ecommerce.productcatalogservice.DTOs.SortParam;
import com.ecommerce.productcatalogservice.DTOs.SortType;
import com.ecommerce.productcatalogservice.Models.Product;
import com.ecommerce.productcatalogservice.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService implements ISearchService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Page<Product> search(String query, int pageNumber, int pageSize, List<SortParam> sortParams) {

        /*Sort sort = null;

        if( sortParams != null && sortParams.size() > 0 ) {
            if(sortParams.get(0).getSortCriteria().equals(SortType.ASC)) {
                sort = Sort.by(sortParams.get(0).getSortCriteria());
            } else {
                sort = Sort.by(sortParams.get(0).getSortCriteria()).descending();
            }
        }

        for(int i = 1; i < sortParams.size(); i++) {
            if( sortParams.get(i).getSortCriteria().equals(SortType.ASC) ) {
                sort = sort.and(Sort.by(sortParams.get(i).getSortCriteria()));
            }  else {
                sort = sort.and(Sort.by(sortParams.get(i).getSortCriteria())).descending();
            }
        }

        return productRepo.findProductByName(query, PageRequest.of(pageNumber, pageSize, sort));
    }*/
        /*Sort sort = null;

        if (sortParams != null && sortParams.size() > 0) {
            if (sortParams.get(0).getSortCriteria().equals(SortType.ASC)) {
                sort = Sort.by(sortParams.get(0).getSortCriteria());
            } else {
                sort = Sort.by(sortParams.get(0).getSortCriteria()).descending();
            }
        }

        for (int i = 1; i < sortParams.size(); i++) {
            if (sortParams.get(i).getSortCriteria().equals(SortType.ASC)) {
                sort = Sort.by(sortParams.get(i).getSortCriteria());
            } else {
                sort = Sort.by(sortParams.get(i).getSortCriteria()).descending();
            }
        }

        return productRepo.findProductByName(query, PageRequest.of(pageNumber, pageSize, sort));
    }*/
        Sort sort = Sort.unsorted();

        if (sortParams != null && !sortParams.isEmpty()) {

            for (SortParam param : sortParams) {

                Sort.Direction direction =
                        param.getSortType() == SortType.DESC
                                ? Sort.Direction.DESC
                                : Sort.Direction.ASC;

                sort = sort.and(Sort.by(direction, param.getSortCriteria()));
            }
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        return productRepo.findProductByName(query, pageRequest);
    }
}
