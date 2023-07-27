package com.infosys.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.orderservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
