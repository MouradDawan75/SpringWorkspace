package fr.dawan.demoapirest.services;

import fr.dawan.demoapirest.dtos.CountDto;
import fr.dawan.demoapirest.dtos.ProductDto;

import java.util.List;

public interface IProductService {
    List<ProductDto> getAllBy(int page, int size, String search) throws Exception;
    CountDto countBy(String search) throws Exception;
    ProductDto saveOrUpdate(ProductDto dto) throws Exception;
    void deleteById(long id) throws Exception;
    ProductDto findById(long id) throws Exception;
}
