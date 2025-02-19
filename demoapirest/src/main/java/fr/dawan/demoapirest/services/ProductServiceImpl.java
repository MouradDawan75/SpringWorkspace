package fr.dawan.demoapirest.services;

import fr.dawan.demoapirest.dtos.CountDto;
import fr.dawan.demoapirest.dtos.ProductDto;
import fr.dawan.demoapirest.entities.Category;
import fr.dawan.demoapirest.entities.Product;
import fr.dawan.demoapirest.repositories.CategoryRepository;
import fr.dawan.demoapirest.repositories.ProductRepository;
import fr.dawan.demoapirest.tools.DtoConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductDto> getAllBy(int page, int size, String search) throws Exception {

        List<ProductDto> result = new ArrayList<>();

        //Dans JpaRepository l'index des pages commence à 0
         List<Product> prods = productRepository.findAllByDescriptionContaining(search, PageRequest.of(page - 1, size)).getContent();
        for(Product p : prods){
            result.add(DtoConverter.convert(p,ProductDto.class));
        }

         return result;
    }

    @Override
    public CountDto countBy(String search) throws Exception {
        long count = productRepository.countByDescriptionContaining(search);
        CountDto countDto = new CountDto();
        countDto.setNb(count);
        return countDto;
    }

    @Override
    public ProductDto saveOrUpdate(ProductDto dto) throws Exception {

        Product p = DtoConverter.convert(dto, Product.class);

        //Gestion du ManyToOne avec Category
        Category cat = categoryRepository.findById(dto.getCategoryId()).get();
        p.setCategory(cat);

        /*
        si id = 0 -> saveAndFlush: exécute la commande insert
        si id != 0 -> saveAndFlush: exécute la commande update
         */

        Product savedProduct = productRepository.saveAndFlush(p);



        return DtoConverter.convert(savedProduct, ProductDto.class);
    }

    @Override
    public void deleteById(long id) throws Exception {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDto findById(long id) throws Exception {

        Optional<Product> optional = productRepository.findById(id);
        if(optional.isPresent()){
            /*
            ProductDto dto = new ProductDto();
            Product p = optional.get();
            dto.setId(p.getId());
            dto.setDescription(p.getDescription());
            dto.setVersion(p.getVersion());
            dto.setImagePath(p.getImagePath());
            dto.setCategoryId(p.getCategory().getId());

            Pour convertir Entity en DTO:
            Option1:
            Definir ses propres méthodes de conversion

            Option2:
            Utiliser des bibliothèques de conversion: ModelMapper - MapStruct
            doc ModelMapper: https://modelmapper.org/getting-started/

             */
/*
            ProductDto dto = DtoConverter.productDtoFromProductEntity(optional.get());

            ModelMapper mapper = new ModelMapper();
            mapper.map(optional.get(), ProductDto.class);

 */

            return DtoConverter.convert(optional.get(), ProductDto.class);
        }

        return null;
    }
}
