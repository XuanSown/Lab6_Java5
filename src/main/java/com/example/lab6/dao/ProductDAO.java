package com.example.lab6.dao;

import com.example.lab6.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Integer> {
    //tìm theo khoảng price
//    @Query("select o from Product o where o.price between ?1 and ?2")
//    List<Product> findByPrice(double min, double max);
    List<Product> findByPriceBetween(double min, double max);

    //tìm theo name và page
//    @Query("select o from Product o where o.name like ?1")
//    Page<Product> findByKeywords(String keyword, Pageable pageable);
    Page<Product> findAllByNameLike(String keyword, Pageable pageable);

}
