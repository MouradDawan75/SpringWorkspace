package fr.dawan.demospringmvc.repositories;


import fr.dawan.demospringmvc.entities.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByDescriptionContaining(String description);
    Page<Product> findByDescriptionContaining(String description, Pageable page);
}
