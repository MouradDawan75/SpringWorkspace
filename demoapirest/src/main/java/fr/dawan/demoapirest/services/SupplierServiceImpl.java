package fr.dawan.demoapirest.services;

import fr.dawan.demoapirest.dtos.CategoryDto;
import fr.dawan.demoapirest.dtos.CountDto;
import fr.dawan.demoapirest.dtos.SupplierDto;
import fr.dawan.demoapirest.entities.Product;
import fr.dawan.demoapirest.entities.Supplier;
import fr.dawan.demoapirest.repositories.ProductRepository;
import fr.dawan.demoapirest.repositories.SupplierRepository;
import fr.dawan.demoapirest.tools.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements ISupplierService{

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<SupplierDto> getAllBy(int page, int size, String search) throws Exception {
        List<SupplierDto> result = new ArrayList<>();

        List<Supplier> suppliers = supplierRepository.findAllByNameContaining(search, PageRequest.of(page - 1, size)).getContent();

        for(Supplier s : suppliers){
            result.add(DtoConverter.convert(s, SupplierDto.class));
        }

        return result;
    }

    @Override
    public CountDto countBy(String search) throws Exception {
        CountDto count = new CountDto();
        long nbre = supplierRepository.countByNameContaining(search);
        count.setNb(nbre);
        return count;
    }

    @Override
    public SupplierDto saveOrUpdate(SupplierDto dto) throws Exception {

        Supplier supplier = DtoConverter.convert(dto, Supplier.class);

        //Gestion du Many To Many avec Product: pour remplir la table de jointure

        for(Long id : dto.getProductsIds()){

            Product p = productRepository.findById(id).get();
            supplier.getProducts().add(p);
            p.getSuppliers().add(supplier);
        }

        supplier.getProducts().remove(null); //suppression des Product null

        Supplier savedSupplier = supplierRepository.saveAndFlush(supplier);


        return DtoConverter.convert(savedSupplier, SupplierDto.class);
    }

    @Override
    public void deleteById(long id) throws Exception {
        supplierRepository.deleteById(id);
    }

    @Override
    public SupplierDto findById(long id) throws Exception {
        Optional<Supplier> optional = supplierRepository.findById(id);
        if(optional.isPresent()){
            return DtoConverter.convert(optional.get(), SupplierDto.class);
        }
        return null;
    }
}
