package com.infosys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

}
