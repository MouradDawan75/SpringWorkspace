package fr.dawan.demoapirest.services;

import fr.dawan.demoapirest.dtos.CategoryDto;
import fr.dawan.demoapirest.dtos.CountDto;
import fr.dawan.demoapirest.dtos.ProductDto;

import java.util.List;

public interface ICategoryService {
    List<CategoryDto> getAllBy(int page, int size, String search) throws Exception;
    CountDto countBy(String search) throws Exception;
    CategoryDto saveOrUpdate(CategoryDto dto) throws Exception;
    void deleteById(long id) throws Exception;
    CategoryDto findById(long id) throws Exception;
}
