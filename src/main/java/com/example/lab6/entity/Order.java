package com.example.lab6.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String address;

    @Temporal(TemporalType.DATE)
    @Column(name = "CreateDate")
    Date createDate = new Date();
    String username;

    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetails;
}
