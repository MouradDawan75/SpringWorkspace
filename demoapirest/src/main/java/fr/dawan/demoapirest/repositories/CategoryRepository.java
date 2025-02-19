package fr.dawan.demoapirest.repositories;

import fr.dawan.demoapirest.entities.Category;
import fr.dawan.demoapirest.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//@Repository: car elle est utilisée dans JpaRepository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /*
    Finder méthodes de Spring Data
    doc:https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
     */


    Page<Category> findAllByNameContaining(String name, Pageable pageable);
    long countByNameContaining(String name);




}
