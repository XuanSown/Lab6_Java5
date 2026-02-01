package com.example.lab6.dao;


import com.example.lab6.entity.OrderDetail;
import com.example.lab6.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long> {
    @Query("SELECT d.product.category AS group, sum(d.price * d.quantity) AS sum, sum(d.quantity) AS count " +
            "FROM OrderDetail d " +
            "GROUP BY d.product.category")
    List<Report> getInventory();
}
