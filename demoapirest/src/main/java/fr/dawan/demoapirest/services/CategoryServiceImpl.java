package fr.dawan.demoapirest.services;

import fr.dawan.demoapirest.dtos.CategoryDto;
import fr.dawan.demoapirest.dtos.CountDto;
import fr.dawan.demoapirest.dtos.ProductDto;
import fr.dawan.demoapirest.entities.Category;
import fr.dawan.demoapirest.entities.Product;
import fr.dawan.demoapirest.repositories.CategoryRepository;
import fr.dawan.demoapirest.repositories.ProductRepository;
import fr.dawan.demoapirest.tools.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService{



    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<CategoryDto> getAllBy(int page, int size, String search) throws Exception {
        List<CategoryDto> result = new ArrayList<>();
        List<Category> categories = categoryRepository.findAllByNameContaining(search, PageRequest.of(page - 1,size)).getContent();

        for(Category cat : categories){
            result.add(DtoConverter.convert(cat, CategoryDto.class));
        }

        return result;
    }

    @Override
    public CountDto countBy(String search) throws Exception {
        CountDto count = new CountDto();
        long nbre = categoryRepository.countByNameContaining(search);
        count.setNb(nbre);
        return count;
    }

    @Override
    public CategoryDto saveOrUpdate(CategoryDto dto) throws Exception {

        Category cat = DtoConverter.convert(dto, Category.class);
        Category savedCategory = categoryRepository.saveAndFlush(cat);

        return DtoConverter.convert(savedCategory, CategoryDto.class);
    }

    @Override
    public void deleteById(long id) throws Exception {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDto findById(long id) throws Exception {
        Optional<Category> optional = categoryRepository.findById(id);
        if(optional.isPresent()){
            return DtoConverter.convert(optional.get(), CategoryDto.class);
        }
        return null;
    }
}
