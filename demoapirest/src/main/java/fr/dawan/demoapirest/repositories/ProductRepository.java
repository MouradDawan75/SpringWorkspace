package fr.dawan.demoapirest.repositories;

import fr.dawan.demoapirest.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

//@Repository: car elle est utilisée dans JpaRepository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /*
    Finder méthodes de Spring Data
    doc:https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
     */


    Page<Product> findAllByDescriptionContaining(String description, Pageable pageable);
    long countByDescriptionContaining(String description);

    //ManyToOne avec Category

    @Query(value = "From Product p where p.category.id = :id")
    List<Product> findAllByCategoryId(@Param("id") long id);

    //Requête sql native

    @Query(nativeQuery = true, value = "Select * From product p INNER JOIN Category c ON (p.category_id = c.id) where c.id = :id")
    List<Product> findAllSqlByCategoryId(@Param("id") long id);


}
