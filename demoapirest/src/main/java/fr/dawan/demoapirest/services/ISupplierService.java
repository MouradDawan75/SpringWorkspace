package fr.dawan.demoapirest.services;

import fr.dawan.demoapirest.dtos.CategoryDto;
import fr.dawan.demoapirest.dtos.CountDto;
import fr.dawan.demoapirest.dtos.SupplierDto;

import java.util.List;

public interface ISupplierService {
    List<SupplierDto> getAllBy(int page, int size, String search) throws Exception;
    CountDto countBy(String search) throws Exception;
    SupplierDto saveOrUpdate(SupplierDto dto) throws Exception;
    void deleteById(long id) throws Exception;
    SupplierDto findById(long id) throws Exception;
}
