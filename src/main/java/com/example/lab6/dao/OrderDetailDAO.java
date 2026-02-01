    package com.example.lab6.dao;


    import com.example.lab6.entity.OrderDetail;
    import com.example.lab6.entity.Report;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;

    import java.util.List;

    public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long> {
        @Query("SELECT o.category AS group, sum(o.price) AS sum, count(o) AS count "
                + " FROM Product o "
                + " GROUP BY o.category"
                + " ORDER BY sum(o.price) DESC")
        List<Report> getInventory();
    }
