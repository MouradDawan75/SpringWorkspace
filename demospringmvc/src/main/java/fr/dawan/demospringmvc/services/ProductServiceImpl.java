package fr.dawan.demospringmvc.services;

import fr.dawan.demospringmvc.entities.Product;
import fr.dawan.demospringmvc.repositories.ProductRepository;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void create(Product p) throws Exception {
        productRepository.saveAndFlush(p);
    }

    @Override
    public void update(Product p) throws Exception {
        productRepository.saveAndFlush(p);
    }

    @Override
    public void delete(Product p) throws Exception {
        productRepository.delete(p);
    }

    @Override
    public List<Product> getAll() throws Exception {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllPaging(int page, int size) throws Exception {
        return productRepository.findAll(PageRequest.of(page-1, size)).getContent();
    }

    @Override
    public List<Product> getAllPagingByDescriptionContaining(int page, int size, String descirption) throws Exception {
        return productRepository.findByDescriptionContaining(descirption, PageRequest.of(page-1, size)).getContent();
    }

    @Override
    public Product getById(long id) throws Exception {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> findByKey(String key) throws Exception {
        return productRepository.findByDescriptionContaining(key);
    }

    public void deleteById(long id) throws Exception  {
        productRepository.deleteById(id);
    }
}
