package com.virtual.soft.axew.repository;

import com.virtual.soft.axew.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM products WHERE name LIKE CONCAT('%', :name, '%')")
    Page<Product> findByName (@Param("name") String name, Pageable pageable);
}
