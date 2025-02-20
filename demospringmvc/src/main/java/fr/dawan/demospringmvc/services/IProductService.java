package fr.dawan.demospringmvc.services;

import fr.dawan.demospringmvc.entities.Product;

import java.util.List;

public interface IProductService {

    void create(Product p) throws Exception;
    void update(Product p) throws Exception;
    void delete(Product p) throws Exception;
    List<Product> getAll() throws Exception;
    List<Product> getAllPaging(int page, int size) throws Exception;
    List<Product> getAllPagingByDescriptionContaining(int page, int size, String descirption) throws Exception;
    Product getById(long id) throws Exception;
    List<Product> findByKey(String key) throws Exception;
    void deleteById(long id) throws Exception;


}
