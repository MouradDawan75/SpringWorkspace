package fr.dawan.demoapirest.tools;

import fr.dawan.demoapirest.dtos.ProductDto;
import fr.dawan.demoapirest.entities.Product;
import org.modelmapper.ModelMapper;

public class DtoConverter {

    private static ModelMapper mapper = new ModelMapper();

    public static ProductDto productDtoFromProductEntity(Product p){
        ProductDto dto = new ProductDto();
        dto.setId(p.getId());
        dto.setDescription(p.getDescription());
        dto.setVersion(p.getVersion());
        dto.setImagePath(p.getImagePath());
        dto.setCategoryId(p.getCategory().getId());
        return dto;
    }

    public static <TSource, TDestination> TDestination convert(TSource obj, Class<TDestination> clazz) throws Exception{

        /*
        Possibilté d'ajouter des règles de mapping personnalisé en cas de besoin
         */

        return mapper.map(obj, clazz);
    }
}
