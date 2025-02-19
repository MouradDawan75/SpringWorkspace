package fr.dawan.demoapirest.repositories;

import fr.dawan.demoapirest.entities.Category;
import fr.dawan.demoapirest.entities.Supplier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

//@Repository: car elle est utilisée dans JpaRepository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    /*
    Finder méthodes de Spring Data
    doc:https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
     */


    Page<Supplier> findAllByNameContaining(String name, Pageable pageable);
    long countByNameContaining(String name);

    /*
    ManyToMany avec Product -> chargement Lazy
     */

    @Query(value = "From Supplier s LEFT JOIN FETCH s.products p where p.id = :prodId")
    List<Supplier> findAllByProductId(@Param("prodId") long id);

    //Chargement des Products

    @Query(value = "From Supplier s LEFT JOIN FETCH s.products p where s.id = :id")
    Optional<Supplier> findById(@Param("id") long id);
}
